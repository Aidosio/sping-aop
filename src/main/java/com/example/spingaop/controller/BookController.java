package com.example.spingaop.controller;

import com.example.spingaop.entity.BookEntity;
import com.example.spingaop.service.BookService;
import com.example.spingaop.util.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookController {

    private final BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/books")
    public CustomResponse<BookEntity> getAll() {
        return service.getAll();
    }

    @GetMapping("/books/{title}")
    public CustomResponse<BookEntity> getBookByTitle(@PathVariable String title) {
        return service.getBookByTitle(title);
    }

    @PostMapping("/books")
    public CustomResponse<BookEntity> addBook(@RequestBody BookEntity book) {
        return service.addBook(book);
    }
}
