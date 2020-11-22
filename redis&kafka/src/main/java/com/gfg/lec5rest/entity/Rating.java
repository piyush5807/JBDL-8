package com.gfg.lec5rest.entity;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@RedisHash("rating")
public class Rating implements Serializable {
    @Id
    Long id;
    Float rating;
}

