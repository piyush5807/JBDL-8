package com.gfg.lec5rest.service;

import com.gfg.lec5rest.model.MovieRequest;
import com.gfg.lec5rest.model.MovieResponse;

public interface MovieService {
    void create(MovieRequest movieRequest);
    MovieResponse get(Long id);
    void update(MovieRequest movieRequest,Long id);
    void delete(Long id);

}
