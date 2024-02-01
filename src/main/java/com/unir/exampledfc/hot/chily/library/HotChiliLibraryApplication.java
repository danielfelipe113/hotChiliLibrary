package com.unir.exampledfc.hot.chily.library;

import com.unir.exampledfc.hot.chily.library.service.DatabaseSeeder;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotChiliLibraryApplication {
	private final DatabaseSeeder databaseSeeder;
	public static void main(String[] args) {
		SpringApplication.run(HotChiliLibraryApplication.class, args);
	}

	public HotChiliLibraryApplication(DatabaseSeeder databaseSeeder) {
		this.databaseSeeder = databaseSeeder;
	}

	@PostConstruct
	public void init() {
		databaseSeeder.seedData();
	}
}
