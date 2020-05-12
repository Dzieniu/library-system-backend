package com.github.dzieniu.libsysbe.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "readers")
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int num_borrowed;

    @Column(nullable = false)
    private int cash_penalty;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne(mappedBy = "reader", fetch = FetchType.LAZY)
    private Reservation reservation;
}
