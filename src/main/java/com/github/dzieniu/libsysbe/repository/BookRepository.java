package com.github.dzieniu.libsysbe.repository;

import com.github.dzieniu.libsysbe.entity.Book;
import com.github.dzieniu.libsysbe.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Reservation> {
}
