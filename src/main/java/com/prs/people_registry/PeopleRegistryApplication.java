package com.prs.people_registry;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PeopleRegistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(PeopleRegistryApplication.class, args);
    }

}
