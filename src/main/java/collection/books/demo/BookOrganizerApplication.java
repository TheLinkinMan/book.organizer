package collection.books.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("collection.books.demo.repository")
public class BookOrganizerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookOrganizerApplication.class, args);
	}

}
