package com.gfg.lec5rest.model;


import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class MovieRequest {
    private String title;
    private String genre;
    private Integer duration;
    private String language;
    private List<CastRequest> casts;
    private List<ReviewRequest> reviewRequests;
    private StreamingDetailsRequest streamingDetails;

}
