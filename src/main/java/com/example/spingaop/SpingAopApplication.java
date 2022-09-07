package com.example.spingaop;

import com.example.spingaop.entity.BookEntity;
import com.example.spingaop.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpingAopApplication implements CommandLineRunner {

	private final BookRepo bookRepo;

	@Autowired
	public SpingAopApplication(BookRepo bookRepo) {
		this.bookRepo = bookRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpingAopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		BookEntity book1 = new BookEntity("Война и мир", "Л.Н. Толстой");
		BookEntity book2 = new BookEntity("Война и мир2", "Л.Н. Толстой2");
		BookEntity book3 = new BookEntity("Война и мир3", "Л.Н. Толстой3");

		bookRepo.save(book1);
		bookRepo.save(book2);
		bookRepo.save(book3);
	}
}
