package com.github.dzieniu.libsysbe.repository;

import com.github.dzieniu.libsysbe.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
}
