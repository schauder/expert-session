package com.example.thorbenexpertsession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback;

import java.util.UUID;

@SpringBootApplication
public class ThorbenExpertSessionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThorbenExpertSessionApplication.class, args);
	}


	@Bean
	BeforeConvertCallback<Minion> createId() {
		return m -> {
			if (m.id == null) {
				m.id = UUID.randomUUID();
			}
			return m;
		};
	}
}
