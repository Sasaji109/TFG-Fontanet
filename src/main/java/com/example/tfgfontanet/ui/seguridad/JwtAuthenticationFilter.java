package com.example.tfgfontanet.ui.seguridad;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.domain.servicios.JwtService;
import com.example.tfgfontanet.ui.errores.excepciones.TokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import static org.apache.logging.log4j.util.Strings.isEmpty;

@Configuration
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
/*
        if (isEmpty(header) || !header.startsWith(Constantes.BEARERHEADER)) {
            filterChain.doFilter(request, response);
            return;
        } */

        String[] values = header.split(Constantes.SPACE);
        if (values[0].equalsIgnoreCase(Constantes.BEARER) && values.length > 1) {

            try {

                Claims claims = jwtService.extractAllClaims(values[1]);

                String rol = (String) claims.get(Constantes.ROLE);
                String name = (String) claims.get(Constantes.USER);
                UserDetails userDetails = User.builder()
                        .username(name)
                        .password(Constantes.SPACE)
                        .roles(rol)
                        .build();

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                SecurityContextHolder.getContext().setAuthentication(auth);
                filterChain.doFilter(request, response);

            } catch (ExpiredJwtException e){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } catch (Exception e) {
                throw new TokenException(e.getMessage());
            }
        }
    }
}
