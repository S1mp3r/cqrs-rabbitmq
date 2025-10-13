package br.rafael.users.api.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandConfig {
    
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
