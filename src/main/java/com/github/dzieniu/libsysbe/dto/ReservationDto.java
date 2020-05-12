package com.github.dzieniu.libsysbe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDto {

    private long id;

    private String reservationDate;

    private String returnDate;

    private long readerId;

    private long bookId;
}
