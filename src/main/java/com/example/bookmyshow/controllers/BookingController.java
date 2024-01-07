package com.example.bookmyshow.controllers;

import com.example.bookmyshow.dtos.BookTicketRequestDto;
import com.example.bookmyshow.dtos.BookTicketResponseDto;
import com.example.bookmyshow.dtos.ResponseStatus;
import com.example.bookmyshow.exceptions.SeatNotAvailableException;
import com.example.bookmyshow.exceptions.UserNotFoundException;
import com.example.bookmyshow.models.Booking;
import com.example.bookmyshow.services.BookingService;

import java.util.List;

public class BookingController {
    BookingService bookingService;
    public BookTicketResponseDto bookTicket(BookTicketRequestDto requestDto){
        long userId = requestDto.getUserId();
        long showId = requestDto.getShowId();
        List<Long> showSeats = requestDto.getShowSeatIds();
        BookTicketResponseDto bookTicketResponseDto = new BookTicketResponseDto();
        Booking booking;
        try{
            booking = bookingService.bookTicket(userId, showId, showSeats);
        }catch (UserNotFoundException | SeatNotAvailableException exception){
            bookTicketResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            bookTicketResponseDto.setMessage(exception.getMessage());
            return bookTicketResponseDto;
        }
        bookTicketResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        bookTicketResponseDto.setBookingId(booking.getId());
        bookTicketResponseDto.setMessage("Ticket is successfully booked");
        return bookTicketResponseDto;
    }
}
