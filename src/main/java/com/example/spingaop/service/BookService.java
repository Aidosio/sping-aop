package com.example.spingaop.service;

import com.example.spingaop.entity.BookEntity;
import com.example.spingaop.repo.BookRepo;
import com.example.spingaop.util.CustomResponse;
import com.example.spingaop.util.CustomStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class BookService {
    private final BookRepo bookRepo;

    @Autowired
    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public CustomResponse<BookEntity> getAll() {
        List<BookEntity> books = bookRepo.findAll();
        return new CustomResponse<>(books, CustomStatus.SUCCESS);
    }

    public CustomResponse<BookEntity> getBookByTitle(String title) {
        BookEntity book = bookRepo.findBookByTitle(title).orElseThrow();
        return new CustomResponse<>(Stream.of(book).collect(Collectors.toList()), CustomStatus.SUCCESS);
    }

    public CustomResponse<BookEntity> addBook(BookEntity book) {
        BookEntity newBook = null;
        newBook = bookRepo.save(book);
        return new CustomResponse<>(Stream.of(newBook).collect(Collectors.toList()), CustomStatus.SUCCESS);
    }
}
