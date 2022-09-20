package ch.zli.coworkingSpace.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.security.Timestamp;

@Getter
@Setter
@Entity(name = "BOOKINGDATES")
public class BookingEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "date", nullable = false)
    private Timestamp date;

    @Column(name = "whole_day", nullable = false)
    private boolean wholeDay;

    @Column(name = "booked", nullable = false)
    private boolean booked;
}