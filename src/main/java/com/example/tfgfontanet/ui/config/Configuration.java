package com.example.tfgfontanet.ui.config;

import com.example.tfgfontanet.common.configuracion.JPAUtil;
import com.example.tfgfontanet.data.dao.implementaciones.DAOUsuariosImpl;
import com.example.tfgfontanet.ui.seguridad.CustomUserDetailsService;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Log4j2
@Data
@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public PasswordEncoder createPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder encoder) {
        var dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(userDetailsService);
        dao.setPasswordEncoder(encoder);
        return dao;
    }

    @Bean
    public UserDetailsService userDetailsService(DAOUsuariosImpl daoUsuariosImpl) {
        return new CustomUserDetailsService(daoUsuariosImpl);
    }

    @Bean
    public JPAUtil jpaUtil() {
        return new JPAUtil();
    }
}
