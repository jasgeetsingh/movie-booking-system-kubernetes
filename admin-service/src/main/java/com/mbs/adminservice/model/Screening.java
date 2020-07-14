package com.mbs.adminservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "screening")
public class Screening extends BaseEntity {


    @OneToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    @JsonIgnore
    private Screen screen;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "is_full")
    private Boolean isFull = false;

    @Column(name = "price")
    private Double price;

    @OneToMany(mappedBy = "screening", cascade = CascadeType.ALL)
    private Set<SeatBooked> bookedSeats;

    @OneToMany(mappedBy = "screening", cascade = CascadeType.ALL)
    private Set<Booking> bookings;

    public LocalDateTime getDate() {
        return date;
    }

    public Boolean getFull() {
        return isFull;
    }

    public Screen getScreen() {
        return screen;
    }





}
