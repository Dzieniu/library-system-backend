package com.github.dzieniu.libsysbe.repository;

import com.github.dzieniu.libsysbe.dto.ReservationInterface;
import com.github.dzieniu.libsysbe.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(nativeQuery = true,
    value = "select reservations.id as reservationId, books.title as bookTitle, books.author as bookAuthor, books.genre as bookGenre,\n" +
            "       books.isbn as bookIsbn, books.status as bookStatus, books.release_date as bookReleaseDate,\n" +
            "       reservations.reservation_date as reservationDate, reservations.return_date as returnDate from reservations\n" +
            "inner join books on reservations.book_id = books.id\n" +
            "inner join readers on reservations.reader_id = readers.id\n" +
            "inner join users on readers.user_id = users.id\n" +
            "where upper(users.email) like concat('%',upper(?1),'%') and return_date is null")
    List<ReservationInterface> findReservation(String search);
}
