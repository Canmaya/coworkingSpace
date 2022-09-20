package ch.zli.coworkingSpace.controller;

import ch.zli.coworkingSpace.model.BookingEntity;
import ch.zli.coworkingSpace.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    @GetMapping("/bookings")
    public ResponseEntity<Iterable<BookingEntity>> getBookingDates() {
        return ResponseEntity
                .status(HttpStatus.OK) // HTTP 200 = OK
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookingService.loadAll());
    }
}
