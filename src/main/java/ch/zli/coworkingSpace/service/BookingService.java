package ch.zli.coworkingSpace.service;

import ch.zli.coworkingSpace.model.BookingEntity;
import ch.zli.coworkingSpace.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookingService {
    private final BookingRepository repository;

    public BookingService(BookingRepository bookingRepository) {
        this.repository = bookingRepository;
    }

    public List<BookingEntity> loadAll() {
        log.info("Executing find all booking dates ...");
        return (List<BookingEntity>) repository.findAll();
    }

    public Optional<BookingEntity> loadOne(Long bookingId) {
        log.info("Executing find booking date with id " + bookingId + " ...");
        return repository.findById(bookingId);
    }

    public BookingEntity create(BookingEntity bookingDate) {
        log.info("Executing create booking date with id " + bookingDate.getId() + " ...");
        return repository.save(bookingDate);
    }

    public BookingEntity update(BookingEntity updateBookingDate) {
        log.info("Executing update booking date with id " + updateBookingDate.getId() + " ...");

        return repository.save(updateBookingDate);
    }

    public void delete(Long bookingDateId) {
        log.info("Executing delete booking with id " + bookingDateId + " ...");
        repository.deleteById(bookingDateId);
    }
}