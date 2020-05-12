package com.github.dzieniu.libsysbe;


import com.github.dzieniu.libsysbe.entity.Book;
import com.github.dzieniu.libsysbe.enums.BookGenre;
import com.github.dzieniu.libsysbe.enums.BookStatus;
import com.github.dzieniu.libsysbe.repository.BookRepository;
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

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Book book1 = Book.builder()
                .title("Sierotka ma rysia")
                .author("Albert Einstein")
                .genre(BookGenre.GATUNEK1)
                .isbn("fdsfsd")
                .status(BookStatus.AVAILABLE)
                .release_date(LocalDateTime.of(1992, 12, 7, 22, 13))
                .build();
        bookRepository.save(book1);
    }
}
