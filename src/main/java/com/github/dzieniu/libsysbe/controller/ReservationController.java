package com.github.dzieniu.libsysbe.controller;

import com.github.dzieniu.libsysbe.dto.ReservationDto;
import com.github.dzieniu.libsysbe.entity.User;
import com.github.dzieniu.libsysbe.service.AuthService;
import com.github.dzieniu.libsysbe.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
    http://localhost:8080/reservations
*/

@RestController
@RequestMapping("reservations")
@CrossOrigin(origins = "*")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private AuthService authService;

    /*
        wyszukiwanie rezerwacji i aktywnych wypozyczen
        przyklad: http://localhost:8080/reservations?email=jacekp@g
    */
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_LIBRARIAN','ROLE_READER')")
    public List<ReservationDto> findReservation(@RequestParam(value = "email", required = false, defaultValue = "") String search){
        User authenticatedUser = authService.getAuthenticatedUser();
        switch (authenticatedUser.getRole()){
            case ROLE_READER: return reservationService.findReservation(authenticatedUser.getEmail());
            case ROLE_LIBRARIAN: return reservationService.findReservation(search);
            default: return new ArrayList<>();
        }
    }

    /*
        rezerwowanie ksiazki przez czytelnika, zmienia status na zarezerwowana
        przyklad: http://localhost:8080/borrow/4
    */
    @PostMapping("borrow/{bookId}")
    @PreAuthorize("hasAnyRole('ROLE_READER')")
    public void borrowBook(@PathVariable long bookId){
        reservationService.reserveBook(bookId,
                authService.getAuthenticatedUser().getReader().getId());
    }

    /*
        wydanie ksiazki przez bibliotekarza, zmienia status na wypozyczona
        przyklad: http://localhost:8080/lend/1
    */
    @PostMapping("lend/{reservationId}")
    @PreAuthorize("hasAnyRole('ROLE_LIBRARIAN')")
    public void lendBook(@PathVariable long reservationId){
        reservationService.lendBook(reservationId);
    }

    /*
        akceptacja zwroconej ksiazki przez bibliotekarza, zmienia status na dostepna
        przyklad: http://localhost:8080/return/1
    */
    @PostMapping("return/{reservationId}")
    @PreAuthorize("hasAnyRole('ROLE_LIBRARIAN')")
    public void returnBook(@PathVariable long reservationId){
        reservationService.returnBook(reservationId);
    }
}
