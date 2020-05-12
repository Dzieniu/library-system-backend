package com.github.dzieniu.libsysbe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {

    private long id;

    private String title;

    private String author;

    private String genre;

    private String isbn;

    private String status;

    private String release_date;
}
