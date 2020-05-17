package com.github.dzieniu.libsysbe.service;

import com.github.dzieniu.libsysbe.dto.ReservationDto;
import com.github.dzieniu.libsysbe.dto.mapper.ReservationMapper;
import com.github.dzieniu.libsysbe.entity.Book;
import com.github.dzieniu.libsysbe.entity.Reader;
import com.github.dzieniu.libsysbe.entity.Reservation;
import com.github.dzieniu.libsysbe.enums.BookStatus;
import com.github.dzieniu.libsysbe.repository.BookRepository;
import com.github.dzieniu.libsysbe.repository.ReaderRepository;
import com.github.dzieniu.libsysbe.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderRepository readerRepository;

    public List<ReservationDto> findReservation(String search){
        return reservationRepository.findReservation(search).stream()
                .map(ReservationMapper::toDto).collect(Collectors.toList());
    }

    // Wypożyczenie książki (czytelnik)
    @Transactional
    public void borrowBook(long bookId, long readerId){
        Optional<Book> bookResult = bookRepository.findById(bookId);
        Optional<Reader> readerResult = readerRepository.findById(readerId);
        if(bookResult.isPresent() && readerResult.isPresent()) {
            Book updatedBook = bookResult.get();
            updatedBook.setStatus(BookStatus.RESERVED);
            bookRepository.save(updatedBook);
            Reservation reservation = new Reservation();
            reservation.setReader(readerResult.get());
            reservation.setBook(bookResult.get());
            reservation.setReservationDate(null);
            reservation.setReturnDate(null);
            reservationRepository.save(reservation);
        } else throw new EntityNotFoundException("Book or reader id is incorrect does not exist, book could not be reserved!");
    }

    // Zatwierdzenie wypożyczenia książki (bibliotekarz)
    @Transactional
    public void lendBook(long reservationId){
        Optional<Reservation> reservationResult = reservationRepository.findById(reservationId);
        if(reservationResult.isPresent()) {
            Reservation reservation = reservationResult.get();
            reservation.setReservationDate(LocalDateTime.now());
            reservation.setReturnDate(null);
            reservationRepository.save(reservation);
            Book book = reservation.getBook();
            book.setStatus(BookStatus.BORROWED);
            Reader reader = reservation.getReader();
            reader.setNumBorrowed(reader.getNumBorrowed() + 1);
            readerRepository.save(reader);
            bookRepository.save(book);
        } else throw new EntityNotFoundException("Reservation with id: " + reservationId + " was not found, and book could not be lent!");
    }

    // Zwrócenie książki (bibliotekarz)
    @Transactional
    public void returnBook(long reservationId){
        Optional<Reservation> reservationResult = reservationRepository.findById(reservationId);
        if(reservationResult.isPresent()) {
            Reservation reservation = reservationResult.get();
            reservation.setReturnDate(LocalDateTime.now());
            reservationRepository.save(reservation);
            Book book = reservation.getBook();
            book.setStatus(BookStatus.AVAILABLE);
            bookRepository.save(book);
            Reader reader = reservation.getReader();
            reader.setNumBorrowed(reader.getNumBorrowed() - 1);
            readerRepository.save(reader);
        } else throw new EntityNotFoundException("Reservation with id: " + reservationId + " was not found, and could not be completed!");
    }
}
