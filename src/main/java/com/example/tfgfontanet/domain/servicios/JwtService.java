package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.data.repositorios.DAOUsuarios;
import com.example.tfgfontanet.domain.modelo.Usuario;
import com.example.tfgfontanet.ui.errores.CustomError;
import com.example.tfgfontanet.ui.errores.excepciones.PrivateKeyException;
import com.example.tfgfontanet.ui.errores.excepciones.TokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.vavr.control.Either;
import org.springframework.stereotype.Service;
import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyStore;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {
    private final DAOUsuarios daoUsuarios;

    public JwtService(DAOUsuarios daoUsuarios) {
        this.daoUsuarios = daoUsuarios;
    }

    private Key clavePrivadaKeyStore() {
        String passwordString = Constantes.KEYSTORE_PASSWORD;
        char[] password = passwordString.toCharArray();
        try (FileInputStream fis = new FileInputStream(Constantes.KEYSTORE_PFX)) {
            KeyStore keyStore = KeyStore.getInstance(Constantes.PKCS_12);
            keyStore.load(fis, password);
            return keyStore.getKey(Constantes.SERVER, password);
        } catch (Exception e) {
            throw new PrivateKeyException(e.getMessage());
        }
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(clavePrivadaKeyStore())
                .build()
                .parseClaimsJws(token)
                .getBody();
    } /*

    public Either<CustomError, String> generateToken(String username, int duration) {
        Either<CustomError, String> res;

        try {
            Usuario user = daoUsuarios.findByUsername(username);
            String token = Jwts.builder()
                    .setSubject(user.getNombre())
                    .setExpiration(Date.from(LocalDateTime.now().plusSeconds(duration).atZone(ZoneId.systemDefault()).toInstant()))
                    .claim(Constantes.USER, user.getNombre())
                    .claim(Constantes.ROLE, user.getRole())
                    .signWith(clavePrivadaKeyStore())
                    .compact();
            res = Either.right(token);
        } catch (Exception e) {
            throw new TokenException(e.getMessage());
        }
        return res;
    }

    public Either<CustomError, String> renovarAccessToken(String refreshToken) {
        Either<CustomError, String> either;
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(clavePrivadaKeyStore()).build().parseClaimsJws(refreshToken).getBody();
            String user = claims.getSubject();
            String accessToken = generateToken(user, Constantes.ACCESS_TOKEN_TIME).get();
            either = Either.right(accessToken);
        } catch (Exception e) {
            either = Either.left(new CustomError("Constantes.ERROR_AL_RENOVAR_EL_TOKEN" + e.getMessage(), LocalDateTime.now()));
        }
        return either;
    } */
}
