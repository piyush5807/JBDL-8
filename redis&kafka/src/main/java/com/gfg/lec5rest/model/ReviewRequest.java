package com.gfg.lec5rest.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReviewRequest {

    private Integer rating;

    private String comment;

    private String userId;

}
