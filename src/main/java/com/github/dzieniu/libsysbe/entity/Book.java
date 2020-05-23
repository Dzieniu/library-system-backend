package com.github.dzieniu.libsysbe.entity;

import com.github.dzieniu.libsysbe.enums.BookGenre;
import com.github.dzieniu.libsysbe.enums.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private BookGenre genre;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private BookStatus status;

    @Column(nullable = false)
    private LocalDateTime releaseDate;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Reservation> reservations = new ArrayList<>();
}
