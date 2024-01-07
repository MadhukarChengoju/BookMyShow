package com.example.bookmyshow.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Seat extends BaseModel{
    private String name ;
    @Column(name = "rowww", nullable = false)
    private long row ;
    private long col ;
    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType ;
    @Enumerated(EnumType.ORDINAL)
    private SeatStatus seatStatus ;
}
