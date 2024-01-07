package com.example.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BookTicketRequestDto {
    //show //list<showseats> //user
    private long userId;
    private long showId;
    private List<Long> showSeatIds;
}
