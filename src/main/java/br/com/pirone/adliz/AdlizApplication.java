package br.com.pirone.adliz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AdlizApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdlizApplication.class, args);
	}
}
