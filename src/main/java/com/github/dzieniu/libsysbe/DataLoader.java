package com.github.dzieniu.libsysbe;


import com.github.dzieniu.libsysbe.entity.*;
import com.github.dzieniu.libsysbe.enums.BookGenre;
import com.github.dzieniu.libsysbe.enums.BookStatus;
import com.github.dzieniu.libsysbe.repository.*;
import com.github.dzieniu.libsysbe.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private LibrarianRepository librarianRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void run(String... args) {

        Book book1 = Book.builder()
                .title("Quo Vadis")
                .author("Henryk Sienkiewicz")
                .genre(BookGenre.powiesc)
                .isbn("978-83-7670-236-0")
                .status(BookStatus.AVAILABLE)
                .releaseDate(LocalDateTime.of(1895, 3, 26, 0, 0))
                .build();
        bookRepository.save(book1);

        Book book2 = Book.builder()
                .title("Ogniem i mieczem")
                .author("Henryk Sienkiewicz")
                .genre(BookGenre.powiesc)
                .isbn("248-81-7670-636-5")
                .status(BookStatus.AVAILABLE)
                .releaseDate(LocalDateTime.of(1884, 7, 1, 0, 0))
                .build();
        bookRepository.save(book2);

        Book book3 = Book.builder()
                .title("Potop")
                .author("Henryk Sienkiewicz")
                .genre(BookGenre.powiesc)
                .isbn("173-84-7650-236-2")
                .status(BookStatus.AVAILABLE)
                .releaseDate(LocalDateTime.of(1992, 12, 7, 0, 0))
                .build();
        bookRepository.save(book3);

        Book book4 = Book.builder()
                .title("Dziady")
                .author("Adam Mickiewicz")
                .genre(BookGenre.powiesc)
                .isbn("558-83-5570-336-4")
                .status(BookStatus.AVAILABLE)
                .releaseDate(LocalDateTime.of(1882, 1, 1, 0, 0))
                .build();
        bookRepository.save(book4);

        Book book5 = Book.builder()
                .title("Pan Tadeusz")
                .author("Adam Mickiewicz")
                .genre(BookGenre.powiesc)
                .isbn("548-83-2270-258-6")
                .status(BookStatus.AVAILABLE)
                .releaseDate(LocalDateTime.of(1883, 1, 1, 0, 0))
                .build();
        bookRepository.save(book5);

        User user1 = User.builder()
                .email("dawidxdzien@gmail.com")
                .password(bCryptPasswordEncoder.encode("madafaka"))
                .role(Role.ROLE_READER)
                .firstName("Dawid")
                .lastName("Dzien")
                .build();
        userRepository.save(user1);
        Reader reader1 = Reader.builder()
                .numBorrowed(0)
                .cashPenalty(0)
                .user(user1)
                .build();
        readerRepository.save(reader1);

        User user2 = User.builder()
                .email("jacekp@gmail.com")
                .password(bCryptPasswordEncoder.encode("jacekp"))
                .role(Role.ROLE_READER)
                .firstName("Jacek")
                .lastName("Placek")
                .build();
        userRepository.save(user2);
        Reader reader2 = Reader.builder()
                .numBorrowed(0)
                .cashPenalty(15)
                .user(user2)
                .build();
        readerRepository.save(reader2);

        User user3 = User.builder()
                .email("lukaszb@gmail.com")
                .password(bCryptPasswordEncoder.encode("bruh"))
                .role(Role.ROLE_LIBRARIAN)
                .firstName("Łukasz")
                .lastName("Betlej")
                .build();
        userRepository.save(user3);
        Librarian librarian1 = Librarian.builder()
                .user(user3)
                .build();
        librarianRepository.save(librarian1);

        Reservation reservation1 = Reservation.builder()
                .isOpen(true)
                .reservationDate(null)
                .returnDate(null)
                .reader(reader1)
                .book(book1)
                .build();
        reservationRepository.save(reservation1);
        book1.setStatus(BookStatus.RESERVED);
        bookRepository.save(book1);

        Reservation reservation2 = Reservation.builder()
                .isOpen(true)
                .reservationDate(LocalDateTime.now())
                .returnDate(LocalDateTime.now().plusWeeks(2))
                .reader(reader1)
                .book(book2)
                .build();
        reservationRepository.save(reservation2);
        book2.setStatus(BookStatus.BORROWED);
        bookRepository.save(book2);
        reader1.setNumBorrowed(1);
        readerRepository.save(reader1);

        Reservation reservation3 = Reservation.builder()
                .isOpen(true)
                .reservationDate(null)
                .returnDate(null)
                .reader(reader2)
                .book(book3)
                .build();
        reservationRepository.save(reservation3);
        book3.setStatus(BookStatus.RESERVED);
        bookRepository.save(book3);
    }
}
