package com.github.dzieniu.libsysbe;


import com.github.dzieniu.libsysbe.entity.Book;
import com.github.dzieniu.libsysbe.entity.Reader;
import com.github.dzieniu.libsysbe.entity.Reservation;
import com.github.dzieniu.libsysbe.entity.User;
import com.github.dzieniu.libsysbe.enums.BookGenre;
import com.github.dzieniu.libsysbe.enums.BookStatus;
import com.github.dzieniu.libsysbe.repository.BookRepository;
import com.github.dzieniu.libsysbe.repository.ReaderRepository;
import com.github.dzieniu.libsysbe.repository.ReservationRepository;
import com.github.dzieniu.libsysbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;


/*

    KLASA POZWALA ZAŁADOWAĆ DO BAZY TESTOWE OBIEKTY

*/

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    @Transactional
    public void run(String... args) {

        Book book1 = Book.builder()
                .title("Sierotka ma rysia")
                .author("Albert Einstein")
                .genre(BookGenre.GATUNEK1)
                .isbn("fdsfsd")
                .status(BookStatus.AVAILABLE)
                .releaseDate(LocalDateTime.of(1992, 12, 7, 22, 13))
                .build();
        bookRepository.save(book1);

        Book book2 = Book.builder()
                .title("Szklankom po lapkach")
                .author("Twoj stary")
                .genre(BookGenre.GATUNEK2)
                .isbn("fdsfsd")
                .status(BookStatus.AVAILABLE)
                .releaseDate(LocalDateTime.of(1992, 12, 7, 22, 13))
                .build();
        bookRepository.save(book2);

        Book book3 = Book.builder()
                .title("Kartony4fun")
                .author("Hanka Mostowiak")
                .genre(BookGenre.GATUNEK1)
                .isbn("fdsfsd")
                .status(BookStatus.AVAILABLE)
                .releaseDate(LocalDateTime.of(1992, 12, 7, 22, 13))
                .build();
        bookRepository.save(book3);

        Book book4 = Book.builder()
                .title("Debesta, tu tego")
                .author("Jan Lesgos")
                .genre(BookGenre.GATUNEK1)
                .isbn("fdsfsd")
                .status(BookStatus.AVAILABLE)
                .releaseDate(LocalDateTime.of(1992, 12, 7, 22, 13))
                .build();
        bookRepository.save(book4);

        User user1 = User.builder()
                .username("jacekp")
                .email("jacekplacek@gmail.com")
                .firstName("Jacek")
                .lastName("Placek")
                .build();
        userRepository.save(user1);

        Reader reader1 = Reader.builder()
                .numBorrowed(0)
                .cashPenalty(0)
                .user(user1)
                .build();
        readerRepository.save(reader1);

        User user2 = User.builder()
                .username("kungro")
                .email("kungro@gmail.com")
                .firstName("Kunegunda")
                .lastName("Grodzka")
                .build();
        userRepository.save(user2);

        Reader reader2 = Reader.builder()
                .numBorrowed(0)
                .cashPenalty(0)
                .user(user2)
                .build();
        readerRepository.save(reader2);

        Reservation reservation1 = Reservation.builder()
                .reservationDate(null)
                .returnDate(null)
                .reader(reader1)
                .book(book1)
                .build();
        reservationRepository.save(reservation1);
        book1.setStatus(BookStatus.RESERVED);
        bookRepository.save(book1);

        Reservation reservation2 = Reservation.builder()
                .reservationDate(null)
                .returnDate(null)
                .reader(reader2)
                .book(book2)
                .build();
        reservationRepository.save(reservation2);
        book2.setStatus(BookStatus.RESERVED);
        bookRepository.save(book2);
    }
}
