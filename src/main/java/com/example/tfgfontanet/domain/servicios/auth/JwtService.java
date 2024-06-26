package com.example.tfgfontanet.domain.servicios.auth;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.data.dao.DAOUsuario;
import com.example.tfgfontanet.data.modelo.UsuarioEntity;
import com.example.tfgfontanet.domain.modelo.mapper.UsuarioEntityMapper;
import com.example.tfgfontanet.domain.modelo.Usuario;
import com.example.tfgfontanet.ui.errores.CustomError;
import com.example.tfgfontanet.ui.errores.excepciones.PrivateKeyException;
import com.example.tfgfontanet.ui.errores.excepciones.TokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final DAOUsuario daoUsuarios;
    private final UsuarioEntityMapper usuarioEntityMapper;

    private Key clavePrivadaKeyStore() {
        String passwordString = Constantes.KEYSTORE_PASSWORD;
        char[] password = passwordString.toCharArray();
        try (InputStream fis = new ClassPathResource(Constantes.KEYSTORE_JKS).getInputStream()) {
            KeyStore keyStore = KeyStore.getInstance(Constantes.JKS);
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
    }

    public Either<CustomError, String> generateToken(String username, int duration) {
        Either<CustomError, String> res;
        try {
            UsuarioEntity usuarioEntity = daoUsuarios.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            Usuario user = usuarioEntityMapper.toUsuario(usuarioEntity);
            String token = Jwts.builder()
                    .setSubject(user.getUsername())
                    .setExpiration(Date.from(LocalDateTime.now().plusSeconds(duration).atZone(ZoneId.systemDefault()).toInstant()))
                    .claim(Constantes.USER, user.getUsername())
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
            either = Either.left(new CustomError(403,Constantes.ERROR_AL_RENOVAR_EL_TOKEN + e.getMessage(), LocalDate.now()));
        }
        return either;
    }
}
