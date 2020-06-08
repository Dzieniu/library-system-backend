package com.github.dzieniu.libsysbe.service;

import com.github.dzieniu.libsysbe.entity.Book;
import com.github.dzieniu.libsysbe.entity.Reader;
import com.github.dzieniu.libsysbe.entity.Reservation;
import com.github.dzieniu.libsysbe.enums.BookGenre;
import com.github.dzieniu.libsysbe.enums.BookStatus;
import com.github.dzieniu.libsysbe.exception.Exception;
import com.github.dzieniu.libsysbe.repository.BookRepository;
import com.github.dzieniu.libsysbe.repository.ReaderRepository;
import com.github.dzieniu.libsysbe.repository.ReservationRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ReaderRepository readerRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldThrowAnExceptionReaderNotFound(){
        Mockito.when(readerRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getBookAvailable()));

        Assertions.assertThrows(Exception.class, () -> reservationService.reserveBook(1, 1));
    }

    @Test
    public void shouldThrowAnExceptionBookNotFound(){
        Mockito.when(readerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getReader1()));
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class, () -> reservationService.reserveBook(1, 1));
    }

    @Test
    public void shouldThrowAnExceptionBookReserved(){
        Mockito.when(readerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getReader1()));
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getBookReserved()));

        Assertions.assertThrows(Exception.class, () -> reservationService.reserveBook(1, 1));
    }

    @Test
    public void shouldThrowAnExceptionBookBorrowed(){
        Mockito.when(readerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getReader1()));
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getBookBorrowed()));

        Assertions.assertThrows(Exception.class, () -> reservationService.reserveBook(1, 1));
    }

    @Test
    public void shouldCorrectlyReserveABook(){
        Mockito.when(readerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getReader1()));
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getBookAvailable()));

        Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenAnswer(invocationOnMock -> {
            Book book = invocationOnMock.getArgument(0);
            Assert.assertEquals(book, getBookReserved());
            return book;
        });
        Mockito.when(reservationRepository.save(Mockito.any(Reservation.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        reservationService.reserveBook(1, 1);
    }

    private Book getBookAvailable(){
        return Book.builder()
                .title("Quo Vadis")
                .author("Henryk Sienkiewicz")
                .genre(BookGenre.powiesc)
                .isbn("978-83-7670-236-0")
                .status(BookStatus.AVAILABLE)
                .releaseDate(LocalDateTime.of(1895, 3, 26, 0, 0))
                .build();
    }

    private Book getBookReserved(){
        return Book.builder()
                .title("Quo Vadis")
                .author("Henryk Sienkiewicz")
                .genre(BookGenre.powiesc)
                .isbn("978-83-7670-236-0")
                .status(BookStatus.RESERVED)
                .releaseDate(LocalDateTime.of(1895, 3, 26, 0, 0))
                .build();
    }

    private Book getBookBorrowed(){
        return Book.builder()
                .title("Quo Vadis")
                .author("Henryk Sienkiewicz")
                .genre(BookGenre.powiesc)
                .isbn("978-83-7670-236-0")
                .status(BookStatus.BORROWED)
                .releaseDate(LocalDateTime.of(1895, 3, 26, 0, 0))
                .build();
    }

    private Reader getReader1(){
        return Reader.builder()
                .numBorrowed(0)
                .cashPenalty(0)
                .user(null)
                .build();
    }

    private Reservation getReservation(){
        return Reservation.builder()
                .isOpen(true)
                .reservationDate(null)
                .returnDate(null)
                .reader(getReader1())
                .book(getBookReserved())
                .build();
    }
}
