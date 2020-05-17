package com.github.dzieniu.libsysbe.dto;

import com.github.dzieniu.libsysbe.enums.BookGenre;
import com.github.dzieniu.libsysbe.enums.BookStatus;

import java.time.LocalDateTime;

public interface ReservationInterface {

    long getReservationId();

    String getBookTitle();

    String getBookAuthor();

    BookGenre getBookGenre();

    String getBookIsbn();

    BookStatus getBookStatus();

    LocalDateTime getBookReleaseDate();

    LocalDateTime getReservationDate();

    LocalDateTime getReturnDate();
}
