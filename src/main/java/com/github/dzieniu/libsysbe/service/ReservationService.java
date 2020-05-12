package com.github.dzieniu.libsysbe.service;

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
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderRepository readerRepository;

    // Wypożyczenie książki (czytelnik)
    public void borrowBook(long bookId, long readerId){
        Optional<Book> bookResult = bookRepository.findById(bookId);
        if(bookResult.isPresent()) {
            Book updatedBook = bookResult.get();
            updatedBook.setStatus(BookStatus.RESERVED);
            bookRepository.save(updatedBook);
        } else throw new EntityNotFoundException("Book with id: " + bookId + " was not found, and could not be reserved!");
    }

    // Zatwierdzenie wypożyczenia książki (bibliotekarz)
    @Transactional
    public void lendBook(long bookId, long readerId){
        Optional<Book> bookResult = bookRepository.findById(bookId);
        Optional<Reader> readerResult = readerRepository.findById(readerId);
        if(bookResult.isPresent() && readerResult.isPresent()) {
            Reservation reservation = new Reservation();
            reservation.setReader(readerResult.get());
            reservation.setBook(bookResult.get());
            reservation.setReservation_date(LocalDateTime.now());
            reservation.setReturn_date(null);
            reservationRepository.save(reservation);
            Book book = bookResult.get();
            book.setStatus(BookStatus.BORROWED);
            Reader reader = readerResult.get();
            reader.setNum_borrowed(reader.getNum_borrowed() + 1);
            readerRepository.save(reader);
            bookRepository.save(book);
        } else throw new EntityNotFoundException("Book with id: " + bookId + " was not found, and could not be lent!");
    }

    // Zwrócenie książki (bibliotekarz)
    @Transactional
    public void returnBook(long reservationId){
        Optional<Reservation> reservationResult = reservationRepository.findById(reservationId);
        if(reservationResult.isPresent()) {
            Reservation reservation = reservationResult.get();
            reservation.setReturn_date(LocalDateTime.now());
            reservationRepository.save(reservation);
            Book book = reservation.getBook();
            book.setStatus(BookStatus.AVAILABLE);
            bookRepository.save(book);
            Reader reader = reservation.getReader();
            reader.setNum_borrowed(reader.getNum_borrowed() - 1);
            readerRepository.save(reader);
        } else throw new EntityNotFoundException("Reservation with id: " + reservationId + " was not found, and could not be completed!");
    }
}
