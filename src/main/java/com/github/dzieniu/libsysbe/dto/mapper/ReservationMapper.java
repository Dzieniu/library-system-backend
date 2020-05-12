package com.github.dzieniu.libsysbe.dto.mapper;

import com.github.dzieniu.libsysbe.dto.ReservationDto;
import com.github.dzieniu.libsysbe.entity.Reservation;

public class ReservationMapper {

    public static ReservationDto toDto(Reservation reservation){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservation.getId());
        if(reservation.getReservation_date()!=null)
            reservationDto.setReservationDate(DateMapper.localDateToString(reservation.getReservation_date()));
        if(reservation.getReturn_date()!=null)
            reservationDto.setReturnDate(DateMapper.localDateToString(reservation.getReturn_date()));
        reservationDto.setReaderId(reservation.getReader().getId());
        reservationDto.setBookId(reservation.getBook().getId());
        return reservationDto;
    }
}
