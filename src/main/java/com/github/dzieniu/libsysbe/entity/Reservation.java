package com.github.dzieniu.libsysbe.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private LocalDateTime reservationDate;

    @Column
    private LocalDateTime returnDate;

    @OneToOne(fetch = FetchType.LAZY)
    private Reader reader;

    @OneToOne(fetch = FetchType.LAZY)
    private Book book;
}
