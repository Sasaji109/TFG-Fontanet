package com.example.tfgfontanet.ui.config;

import graphql.schema.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphqlConfig {

    private final GraphQLScalarType doubleScalar;
    private final GraphQLScalarType localDateTimeScalar;
    private final GraphQLScalarType localDateScalar;

    public GraphqlConfig(GraphQLScalarType doubleScalar, GraphQLScalarType localDateTimeScalar, GraphQLScalarType localDateScalar) {
        this.doubleScalar = doubleScalar;
        this.localDateTimeScalar = localDateTimeScalar;
        this.localDateScalar = localDateScalar;
    }

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
                .scalar(doubleScalar)
                .scalar(localDateTimeScalar)
                .scalar(localDateScalar);
    }
}

