package com.github.dzieniu.libsysbe.repository;

import com.github.dzieniu.libsysbe.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(nativeQuery = true,
    value = "select * from reservations where id in (select result.id from (select reservations.id,  users.first_name, users.last_name from reservations\n" +
            "inner join readers on reservations.reader_id = readers.id\n" +
            "inner join users on readers.user_id = users.id) result where UPPER(CONCAT(result.first_name,' ', result.last_name)) like CONCAT('%',UPPER(?1),'%'))")
    List<Reservation> findReservation(String search);
}
