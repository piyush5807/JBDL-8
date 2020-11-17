package com.gfg.lec5rest.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "movie")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    private String title;

    @Column(length = 16)
    private String genre;

    @OneToMany(mappedBy = "movies", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "movie_cast",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "cast_id"))
    private Set<Cast> casts;

    @OneToOne(mappedBy = "movie",cascade = CascadeType.ALL)
    private StreamingDetails streamingDetails;

    private Integer duration;

    private String language;
}
