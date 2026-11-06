package com.amv.book_service.controller;

import com.amv.book_service.model.Book;
import com.amv.book_service.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book endpoint")
@RestController
@RequestMapping("book-service")
@RequiredArgsConstructor
public class BookController {
    private final BookService service;

    @Operation(summary = "Find a specific book by your ID")
    @GetMapping(value = "/{id}/{currency}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
        return service.findBookAndCurrency(id, currency);
    }
}
