package com.github.dzieniu.libsysbe.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // narazie zrobie login po username'ie, potem ew. przerobie na email i to pole można dropnąć
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Reader reader;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Librarian librarian;
}
