package com.github.dzieniu.libsysbe.repository;

import com.github.dzieniu.libsysbe.entity.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Long> {
}
