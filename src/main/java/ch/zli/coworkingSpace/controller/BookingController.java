package ch.zli.coworkingSpace.controller;


import ch.zli.coworkingSpace.model.BookingEntity;
import ch.zli.coworkingSpace.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Operation(
            summary = "Get all Bookings",
            description = "Loads all Bookings from database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @GetMapping("/bookings")
    public ResponseEntity<Iterable<BookingEntity>> getBooking() {
        return ResponseEntity
                .status(HttpStatus.OK) // HTTP 200 = OK
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookingService.loadAll());
    }

    @Operation(
            summary = "Get one specific booking by id",
            description = "Loads one specific booking by id from database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @GetMapping("/bookings/{id}")
    public ResponseEntity<Optional<BookingEntity>>
    getBooking(@PathVariable long id) {
        Optional<BookingEntity> booking = bookingService.loadOne(id);

        if (booking.isPresent()) {
            System.out.println("Accessing single date, HTTP: 200");
            return ResponseEntity
                    .status(HttpStatus.OK)  // HTTP 200
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(booking);
        } else {
            System.out.println("Accessing single date, HTTP: 404");
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Create a new booking",
            description = "Creates a new booking in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/bookings")
    public ResponseEntity<BookingEntity>
    addBooking(@RequestBody BookingEntity bookingDate) {
        System.out.println("booking created");

        bookingService.create(bookingDate);
        return ResponseEntity
                .status(HttpStatus.CREATED)  // HTTP 201
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookingDate);
    }

    @Operation(
            summary = "Update an existing booking",
            description = "Updates one specific and existing booking in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/bookings/{id}")
    public ResponseEntity<BookingEntity>
    updateBookingDate(@RequestBody BookingEntity bookingDate) {

        bookingService.create(bookingDate);
        return ResponseEntity.status(HttpStatus.CREATED)  // HTTP 201
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookingDate);
    }

    @Operation(
            summary = "Delete an existing booking",
            description = "Deletes one specific and existing booking in database.",
            security = {@SecurityRequirement(name = "JWT Auth")}
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<?>
    deleteBooking(@PathVariable long id) {
        Optional<BookingEntity> bookingDate = bookingService.loadOne(id);

        if (bookingDate.isPresent()) {
            System.out.println("deleted bookingDate");
            bookingService.delete(id);
            return ResponseEntity.noContent().build();  // HTTP 204
        } else {
            return ResponseEntity.notFound().build();   // HTTP 404
        }
    }
}
