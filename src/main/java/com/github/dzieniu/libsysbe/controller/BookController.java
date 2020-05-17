package com.github.dzieniu.libsysbe.controller;

import com.github.dzieniu.libsysbe.dto.BookDto;
import com.github.dzieniu.libsysbe.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    http://localhost:8080/books
*/

@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookService bookService;

    // wyszukiwanie książek
    @GetMapping
    public List<BookDto> findBook(@RequestParam(value = "search", required = false) String searchCriteria){
        return bookService.findBook(searchCriteria);
    }

    // dodawanie
    @PostMapping
    public void insertBook(@RequestBody BookDto bookDto){
        bookService.insertBook(bookDto);
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable long id, @RequestBody BookDto bookDto){
        bookService.updateBook(id, bookDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id){
        bookService.deleteBook(id);
    }
}
