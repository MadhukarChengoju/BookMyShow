package com.example.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ShowSeatTypePrice extends BaseModel {
    @ManyToOne
    private Show show;
    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;
    private long price;

}
