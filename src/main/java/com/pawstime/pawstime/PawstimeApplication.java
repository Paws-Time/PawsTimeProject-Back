package com.pawstime.pawstime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PawstimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PawstimeApplication.class, args);
	}

}
