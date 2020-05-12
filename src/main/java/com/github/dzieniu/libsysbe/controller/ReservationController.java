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

    @GetMapping
    public List<ReservationDto> findReservation(@RequestParam(value = "search", required = false, defaultValue = "") String search){
        return reservationService.findReservation(search);
    }

    @PostMapping("borrow")
    public void borrowBook(@RequestBody BookReaderIds bookReaderIds){
        reservationService.borrowBook(bookReaderIds.getBookId(), bookReaderIds.getReaderId());
    }

    @PostMapping("lend/{reservationId}")
    public void lendBook(@PathVariable long reservationId){
        reservationService.lendBook(reservationId);
    }

    @PostMapping("return/{reservationId}")
    public void returnBook(@PathVariable long reservationId){
        reservationService.returnBook(reservationId);
    }
}
