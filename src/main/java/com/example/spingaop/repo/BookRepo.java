package com.example.spingaop.repo;

import com.example.spingaop.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepo extends JpaRepository<BookEntity, Long> {
    Optional<BookEntity> findBookByTitle(String title);
}
