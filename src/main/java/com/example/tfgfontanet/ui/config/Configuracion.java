package com.example.tfgfontanet.ui.config;

import com.example.tfgfontanet.common.configuracion.ConfiguracionMail;
import com.example.tfgfontanet.common.configuracion.JPAUtil;
import com.example.tfgfontanet.common.utiles.mail.MandarMail;
import com.example.tfgfontanet.data.dao.DAOUsuario;
import com.example.tfgfontanet.domain.modelo.mapper.UsuarioEntityMapper;
import com.example.tfgfontanet.ui.seguridad.CustomUserDetailsService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Log4j2
@Data
@Configuration
public class Configuracion {

    @Bean
    public PasswordEncoder encoder() {
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
    public UserDetailsService userDetailsService(DAOUsuario daoUsuarios, UsuarioEntityMapper usuarioEntityMapper) {
        return new CustomUserDetailsService(daoUsuarios, usuarioEntityMapper);
    }

    @Bean
    public JPAUtil jpaUtil() {
        return new JPAUtil();
    }


    @Bean
    public MongoDatabase getMongoDatabase() {
        MongoClient mongoClient = MongoClients.create("mongodb://informatica.iesquevedo.es:2323");
        return mongoClient.getDatabase("samuelsanchez_tfg");
    }

    @Bean
    public ConfiguracionMail configuracionMail() {
        return new ConfiguracionMail();
    }

    @Bean
    public MandarMail mandarMail(ConfiguracionMail config) {
        return new MandarMail(config);
    }
}
