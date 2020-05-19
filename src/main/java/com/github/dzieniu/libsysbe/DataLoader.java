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

        User user1 = User.builder()
                .email("jacekp@gmail.com")
                .password(bCryptPasswordEncoder.encode("jacekp"))
                .role(Role.ROLE_READER)
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
                .email("kungro@gmail.com")
                .password(bCryptPasswordEncoder.encode("kungro"))
                .role(Role.ROLE_READER)
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
                .reader(reader1)
                .book(book2)
                .build();
        reservationRepository.save(reservation2);
        book2.setStatus(BookStatus.BORROWED);
        bookRepository.save(book2);
    }
}
