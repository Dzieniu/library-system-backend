package com.github.dzieniu.libsysbe.controller;

import com.github.dzieniu.libsysbe.dto.BookDto;
import com.github.dzieniu.libsysbe.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    http://localhost:8080/books
*/

@RestController
@RequestMapping("books")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookService bookService;

    /*
        wyszukiwanie książek
        przyklad: http://localhost:8080/books?search=author%mickiewicz,status:available&sort=releaseDate
    */
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_READER','ROLE_LIBRARIAN')")
    public List<BookDto> findBook(
            @RequestParam(value = "search", required = false) String searchCriteria,
            @PageableDefault(size = 20, direction = Sort.Direction.ASC, sort = "title", value = 30) Pageable pageable
    ){
        return bookService.findBook(searchCriteria, pageable);
    }

    /*
        dodawanie książki
        przyklad: http://localhost:8080/books
        {
            "title":"trolololo",
            "author":"Krzysztof Gonciarz",
            "genre":"GATUNEK1",
            "isbn":"sgsgregre",
            "releaseDate":"07-12-1992"
        }
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_LIBRARIAN')")
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
    @PreAuthorize("hasAnyRole('ROLE_LIBRARIAN')")
    public void updateBook(@PathVariable long id, @RequestBody BookDto bookDto){
        bookService.updateBook(id, bookDto);
    }

    /*
        usuniecie ksiazki
        przyklad: http://localhost:8080/books/5
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_LIBRARIAN')")
    public void deleteBook(@PathVariable long id){
        bookService.deleteBook(id);
    }
}
