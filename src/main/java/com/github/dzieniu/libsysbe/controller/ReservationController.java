package com.github.dzieniu.libsysbe.controller;

import com.github.dzieniu.libsysbe.dto.BookReaderIds;
import com.github.dzieniu.libsysbe.dto.ReservationDto;
import com.github.dzieniu.libsysbe.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    http://localhost:8080/reservations
*/

@RestController
@RequestMapping("reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    /*
        wyszukiwanie rezerwacji i aktywnych wypozyczen
        przyklad: http://localhost:8080/reservations?email=jacekp@g
    */
    @GetMapping
    public List<ReservationDto> findReservation(@RequestParam(value = "email", required = false, defaultValue = "") String search){
        return reservationService.findReservation(search);
    }

    /*
        rezerwowanie ksiazki przez czytelnika, zmienia status na zarezerwowana
        przyklad: http://localhost:8080/borrow
        {
            "bookId":3,
            "readerId":3
        }
    */
    @PostMapping("borrow")
    public void borrowBook(@RequestBody BookReaderIds bookReaderIds){
        reservationService.borrowBook(bookReaderIds.getBookId(), bookReaderIds.getReaderId());
    }

    /*
        wydanie ksiazki przez bibliotekarza, zmienia status na wypozyczona
        przyklad: http://localhost:8080/lend/1
    */
    @PostMapping("lend/{reservationId}")
    public void lendBook(@PathVariable long reservationId){
        reservationService.lendBook(reservationId);
    }

    /*
        akceptacja zwroconej ksiazki przez bibliotekarza, zmienia status na dostepna
        przyklad: http://localhost:8080/return/1
    */
    @PostMapping("return/{reservationId}")
    public void returnBook(@PathVariable long reservationId){
        reservationService.returnBook(reservationId);
    }
}
