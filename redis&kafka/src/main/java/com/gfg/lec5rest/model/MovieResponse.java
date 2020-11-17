package com.gfg.lec5rest.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class MovieResponse {
    private String title;
    private String genre;
    private Integer duration;
    private String language;
    private List<CastResponse> casts;
    private StreamingDetailsResponse streamingDetails;
}
