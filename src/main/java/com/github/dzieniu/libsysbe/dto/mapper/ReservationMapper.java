package com.github.dzieniu.libsysbe.dto.mapper;

import com.github.dzieniu.libsysbe.dto.ReservationDto;
import com.github.dzieniu.libsysbe.dto.ReservationInterface;

public class ReservationMapper {

    public static ReservationDto toDto(ReservationInterface reservation){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setReservationId(reservation.getReservationId());
        reservationDto.setBookTitle(reservation.getBookTitle());
        reservationDto.setBookAuthor(reservation.getBookAuthor());
        reservationDto.setBookGenre(reservation.getBookGenre().name());
        reservationDto.setBookIsbn(reservation.getBookIsbn());
        reservationDto.setBookStatus(reservation.getBookStatus().name());
        reservationDto.setBookReleaseDate(DateMapper.localDateToString(reservation.getBookReleaseDate()));
        if(reservation.getReservationDate()!=null) reservationDto.setReservationDate(DateMapper.localDateToString(reservation.getReservationDate()));
        if(reservation.getReturnDate()!=null) reservationDto.setReturnDate(DateMapper.localDateToString(reservation.getReturnDate()));
        return reservationDto;
    }
}
