package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
class DemoApplicationTests {

	@Container
	public static PostgreSQLContainer container = new PostgreSQLContainer()
			.withDatabaseName("test")
			.withUsername("test")
			.withPassword("test");

	@Autowired
	private BookRepository bookRepository;

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.username", container::getUsername);
		registry.add("spring.datasource.password", container::getPassword);
	}

	@Test
	void contextLoads() {

		Book book = new Book("title", "author");
		bookRepository.save(book);

		Book foundBook = bookRepository.findById(book.getId());

		System.out.println("Context Loads!");
	}

}
