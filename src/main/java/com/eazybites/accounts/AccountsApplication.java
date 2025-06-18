package com.eazybites.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl") // Habilita JPA Auditing
@OpenAPIDefinition(info = @Info(
	title = "Accounts Microservice REST API Definition",
	description = "Eazy Bank Accounts Microservice REST API Documentation",
	version = "v1",
	contact = @Contact(
		name = "José Alberto López Esteva",
		email = "alberto.esteva88@gmail.com",
		url = "https://www.linkedin.com/in/jose-alberto-lopez-esteva-101ba8354/"
	),
	license = @License(
		name = "Apache 2.0",
		url = "https://www.apache.org/licenses/LICENSE-2.0"
	)
	),
	externalDocs = @ExternalDocumentation(
		description = "Eazy Bank Accounts Microservice REST API Documentation",
		url = "https://www.eazybytes.com/swagger-ui.html"
	)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
