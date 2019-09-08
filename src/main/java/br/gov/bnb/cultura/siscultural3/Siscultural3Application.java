package br.gov.bnb.cultura.siscultural3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@SpringBootApplication
@EnableAutoConfiguration
public class Siscultural3Application {

	public static void main(String[] args) {
		SpringApplication.run(Siscultural3Application.class, args);
	}
}
