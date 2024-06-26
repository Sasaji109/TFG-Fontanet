package com.example.tfgfontanet.ui.config;

import com.example.tfgfontanet.common.Constantes;
import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class GraphQLScalarConfiguration {

    @Bean
    public GraphQLScalarType doubleScalar() {
        return GraphQLScalarType.newScalar()
                .name(Constantes.DOUBLE)
                .description(Constantes.DOUBLE_SCALAR)
                .coercing(new Coercing<Double, Double>() {
                    @Override
                    public Double serialize(Object dataFetcherResult) {
                        return ((Number) dataFetcherResult).doubleValue();
                    }

                    @Override
                    public Double parseValue(Object input) {
                        return ((Number) input).doubleValue();
                    }

                    @Override
                    public Double parseLiteral(Object input) {
                        return ((Number) input).doubleValue();
                    }
                })
                .build();
    }

    @Bean
    public GraphQLScalarType localDateTimeScalar() {
        return GraphQLScalarType.newScalar()
                .name(Constantes.LOCAL_DATE_TIME)
                .description(Constantes.LOCAL_DATE_TIME_SCALAR)
                .coercing(new Coercing<LocalDateTime, String>() {
                    @Override
                    public String serialize(Object dataFetcherResult) {
                        return ((LocalDateTime) dataFetcherResult).toString();
                    }

                    @Override
                    public LocalDateTime parseValue(Object input) {
                        return LocalDateTime.parse(input.toString());
                    }

                    @Override
                    public LocalDateTime parseLiteral(Object input) {
                        return LocalDateTime.parse(input.toString());
                    }
                })
                .build();
    }

    @Bean
    public GraphQLScalarType localDateScalar() {
        return GraphQLScalarType.newScalar()
                .name(Constantes.LOCAL_DATE)
                .description(Constantes.LOCAL_DATE_SCALAR)
                .coercing(new Coercing<LocalDate, String>() {
                    @Override
                    public String serialize(Object dataFetcherResult) {
                        return ((LocalDate) dataFetcherResult).toString();
                    }

                    @Override
                    public LocalDate parseValue(Object input) {
                        return LocalDate.parse(input.toString());
                    }

                    @Override
                    public LocalDate parseLiteral(Object input) {
                        return LocalDate.parse(input.toString());
                    }
                })
                .build();
    }
}
