package com.mbs.adminservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "duration")
    private Integer duration;

}
