package com.gfg.lec5rest.repository;


import com.gfg.lec5rest.entity.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository extends CrudRepository<Movie,Long> {
}
