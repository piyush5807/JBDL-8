package com.gfg.lec5rest.repository;


import com.gfg.lec5rest.entity.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie,Long> {
}
