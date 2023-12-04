package io.algaworksalgafoodjava;

import io.algaworksalgafoodjava.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AlgaworksAlgafoodJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgaworksAlgafoodJavaApplication.class, args);
	}

}

