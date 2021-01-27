package com.ordemservico.osworks.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration  // componente spring com objetivo de configuraçao spring
public class ModelMapperConfig {

    @Bean  // instancia um Bean do tipo spring - fazendo isso o spring instanciaa o ModelMapping
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
