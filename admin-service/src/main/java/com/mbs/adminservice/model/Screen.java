package com.mbs.adminservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "screen")
public class Screen extends BaseEntity{


    @Column(name = "seat_count")
    private Integer seatCount;

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    @JsonIgnore
    private Theatre theatre;

    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL)
    private Set<Seat> seats;

    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL)
    private Set<Screening> screenings;

    public Set<Seat> getSeats() {
        return seats;
    }

    public void addSeat(Seat seat){
        seat.setScreen(this);
    }
    public void addScreening(Screening screening){
        screening.setScreen(this);
    }
}
