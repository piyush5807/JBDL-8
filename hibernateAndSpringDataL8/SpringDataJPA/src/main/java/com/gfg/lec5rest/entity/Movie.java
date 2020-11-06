package com.gfg.lec5rest.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String title;

    @Column(length = 16)
    private String genre;

    private Integer rating;

    @Column(name = "release_date")
    private Date releaseDate;

    private Integer duration;

    private String language;

    public Movie() {
    }
}
