package com.gfg.lec5rest.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class MovieRequest {
    private String title;
    private String genre;
    private Integer rating;
    private String releaseDate;
    private Integer duration;
    private String language;
}
