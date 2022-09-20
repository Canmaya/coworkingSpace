package ch.zli.coworkingSpace.repository;

import ch.zli.coworkingSpace.model.BookingEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<BookingEntity, Long> {
}