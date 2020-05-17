package com.github.dzieniu.libsysbe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDto {

    private long reservationId;

    private String bookTitle;

    private String bookAuthor;

    private String bookGenre;

    private String bookIsbn;

    private String bookStatus;

    private String bookReleaseDate;

    private String reservationDate;

    private String returnDate;
}
