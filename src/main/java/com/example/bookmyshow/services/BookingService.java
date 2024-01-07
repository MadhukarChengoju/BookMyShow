package com.example.bookmyshow.services;

import com.example.bookmyshow.exceptions.SeatNotAvailableException;
import com.example.bookmyshow.exceptions.UserNotFoundException;
import com.example.bookmyshow.models.*;
import com.example.bookmyshow.repositories.ShowRepository;
import com.example.bookmyshow.repositories.ShowSeatRepository;
import com.example.bookmyshow.repositories.UserRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class BookingService {
    UserRepository userRepository;
    ShowRepository showRepository;
    ShowSeatRepository showSeatRepository;
    PaymentService paymentService;
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookTicket(long userId, long showId, List<Long> showSeats) throws UserNotFoundException, SeatNotAvailableException {
        //validate userId
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("No user found for the provided UserId");
        }
        User user = optionalUser.get();
        Show show = showRepository.findById(showId).get();
        List<ShowSeat> bookingSeats = showSeatRepository.findAllById(showSeats);
        for(ShowSeat showSeat:bookingSeats){
            if(showSeat.getShowSeatStatus().equals(ShowSeatStatus.BOOKED) ||
                    (showSeat.getShowSeatStatus().equals(ShowSeatStatus.BLOCKED) &&
                            ((System.currentTimeMillis()-showSeat.getModifiedAt().getTime())/60000)<=10)){
                throw new SeatNotAvailableException("Selected seats are not available at the moment");
            }
        }
        for(ShowSeat showSeat:bookingSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeat.setModifiedAt(new Date());
            showSeatRepository.save(showSeat);
        }
        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.IN_PROGRESS);
        booking.setShowSeats(bookingSeats);
        booking.setShow(show);
        booking.setUser(user);
        booking.setAmount(paymentService.calculateAmount(show, bookingSeats));

        return null;
    }
}
