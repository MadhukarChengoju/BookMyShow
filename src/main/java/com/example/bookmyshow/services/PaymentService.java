package com.example.bookmyshow.services;

import com.example.bookmyshow.models.Show;
import com.example.bookmyshow.models.ShowSeat;
import com.example.bookmyshow.models.ShowSeatTypePrice;
import com.example.bookmyshow.repositories.ShowSeatTypePriceRepository;

import java.util.List;

public class PaymentService {
    ShowSeatTypePriceRepository showSeatTypePriceRepository;
    public long calculateAmount(Show show, List<ShowSeat> showSeats) {
        List<ShowSeatTypePrice> showSeatTypePrices = showSeatTypePriceRepository.findAllByShow(show);
        long amount = 0;
        for(ShowSeat showSeat:showSeats){
            for(ShowSeatTypePrice showSeatTypePrice:showSeatTypePrices){
                if(showSeatTypePrice.getSeatType().equals(showSeat.getSeat().getSeatType())){
                    amount+= showSeatTypePrice.getPrice();
                }
            }
        }
        return amount;
    }
}
