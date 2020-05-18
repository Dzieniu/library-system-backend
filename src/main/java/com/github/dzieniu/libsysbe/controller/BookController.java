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

    /*
        wyszukiwanie książek
        przyklad: http://localhost:8080/books?search=title%sierotka,status:available
    */
    @GetMapping
    public List<BookDto> findBook(@RequestParam(value = "search", required = false) String searchCriteria){
        return bookService.findBook(searchCriteria);
    }

    /*
        dodawanie książki
        przyklad: http://localhost:8080/books
        {
            "title":"trolololo",
            "author":"Krzysztof Gonciarz",
            "genre":"GATUNEK1",
            "isbn":"sgsgregre",
            "releaseDate":"07-12-1992 22:13"
        }
     */
    @PostMapping
    public void insertBook(@RequestBody BookDto bookDto){
        bookService.insertBook(bookDto);
    }

    /*
        modyfikacja ksiazki
        przyklad: http://localhost:8080/books/5
        {
            "title":"Przewodnik po remoncie",
            "author":"Wieslaw Paleta"
        }
     */
    @PutMapping("/{id}")
    public void updateBook(@PathVariable long id, @RequestBody BookDto bookDto){
        bookService.updateBook(id, bookDto);
    }

    /*
        usuniecie ksiazki
        przyklad: http://localhost:8080/books/5
     */
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id){
        bookService.deleteBook(id);
    }
}
