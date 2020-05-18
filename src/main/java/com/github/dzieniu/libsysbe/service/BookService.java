package com.github.dzieniu.libsysbe.service;

import com.github.dzieniu.libsysbe.dto.BookDto;
import com.github.dzieniu.libsysbe.dto.mapper.BookMapper;
import com.github.dzieniu.libsysbe.dto.mapper.DateMapper;
import com.github.dzieniu.libsysbe.entity.Book;
import com.github.dzieniu.libsysbe.enums.BookGenre;
import com.github.dzieniu.libsysbe.enums.BookStatus;
import com.github.dzieniu.libsysbe.repository.BookRepository;
import com.github.dzieniu.libsysbe.repository.spec.BookSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Wyszukanie ksiązki (bibliotekarz/czytelnik)
    public List<BookDto> findBook(String searchCriteria){
        Specification<Book> spec = new BookSpecificationBuilder<Book>().buildSpecification(searchCriteria);
        return bookRepository.findAll(spec).stream()
                .map(BookMapper::toDto).collect(Collectors.toList());
    }

    // Dodanie książki (bibliotekarz)
    public void insertBook(BookDto bookDto){
        Book book = BookMapper.toEntity(bookDto);
        book.setStatus(BookStatus.AVAILABLE);
        bookRepository.save(book);
    }

    // Edycja książki (bibliotekarz)
    public void updateBook(long id, BookDto bookDto){
        Optional<Book> bookResult = bookRepository.findById(id);
        if(bookResult.isPresent()) {
            Book updatedBook = bookResult.get();
            if(bookDto.getTitle()!=null) updatedBook.setTitle(bookDto.getTitle());
            if(bookDto.getAuthor()!=null) updatedBook.setAuthor(bookDto.getAuthor());
            if(bookDto.getGenre()!=null) updatedBook.setGenre(BookGenre.valueOf(bookDto.getGenre()));
            if(bookDto.getIsbn()!=null) updatedBook.setIsbn(bookDto.getIsbn());
            if(bookDto.getStatus()!=null) updatedBook.setStatus(BookStatus.valueOf(bookDto.getStatus()));
            if(bookDto.getReleaseDate()!=null) updatedBook.setReleaseDate(DateMapper.stringToLocalDate(bookDto.getReleaseDate()));
            bookRepository.save(updatedBook);
        } else throw new EntityNotFoundException("Book with id: " + id + " was not found, and could not be updated!");
    }

    // Usunięcie książki (bibliotekarz)
    public void deleteBook(long id){
        Optional<Book> bookResult = bookRepository.findById(id);
        if(bookResult.isPresent()) {
            bookRepository.delete(bookResult.get());
        } else throw new EntityNotFoundException("Book with id: " + id + " was not found, and could not be deleted!");
    }
}
