package com.github.dzieniu.libsysbe.dto.mapper;

import com.github.dzieniu.libsysbe.dto.BookDto;
import com.github.dzieniu.libsysbe.entity.Book;
import com.github.dzieniu.libsysbe.enums.BookGenre;
import com.github.dzieniu.libsysbe.enums.BookStatus;

public class BookMapper {

    public static BookDto toDto(Book book){
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .genre(book.getGenre().name())
                .isbn(book.getIsbn())
                .status(book.getStatus().name())
                .release_date(DateMapper.localDateToString(book.getReleaseDate()))
                .build();
    }

    public static Book toEntity(BookDto bookDto){
        return Book.builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .genre(BookGenre.valueOf(bookDto.getGenre()))
                .isbn(bookDto.getIsbn())
                .status(BookStatus.valueOf(bookDto.getStatus()))
                .releaseDate(DateMapper.stringToLocalDate(bookDto.getRelease_date()))
                .build();
    }
}
