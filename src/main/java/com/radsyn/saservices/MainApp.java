package com.radsyn.saservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/***
import com.radsyn.saservices.domain.Country;
import com.radsyn.saservices.repo.CountryRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
***/

@SpringBootApplication
@EnableJpaAuditing
public class MainApp {
	private static final Logger log = LoggerFactory.getLogger(MainApp.class);

	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}

	@Bean
	public OpenAPI springShopOpenAPI() {
	      return new OpenAPI()
	              .info(new Info().title("SA Services API")
	              .description("SA services application")
	              .version("v1.0.0")
	              .license(new License().name("Apache 2.0").url("http://springdoc.org")))
	              .externalDocs(new ExternalDocumentation()
	              .description("SASVCS Wiki Documentation")
	              .url("https://xxxxxx.com/docs"));
	}


	
	/*@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {

			repository.save(new Customer("Umesh", "Awasthi","email@test.com"));
			repository.save(new Customer("David", "Dobrik", "email1@test.com"));
			repository.save(new Customer("Robert", "Hickle","r.k@email.com"));
			repository.save(new Customer("Edgar", "Smith","edgar@email.com"));

			// fetch all customers

			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
		};
	}*/
}
