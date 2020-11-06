package com.gfg.lec5rest.resources;

import com.gfg.lec5rest.entity.Movie;
import com.gfg.lec5rest.model.MovieRequest;
import com.gfg.lec5rest.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@Slf4j
public class MovieController {

    @Autowired
    MovieRepository repository;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @PostMapping("/v1/movie")
    void create(@RequestBody MovieRequest movieRequest) throws ParseException {

        log.info("received movie request for movie {}"
                , movieRequest.getTitle());

        Movie movie = new Movie();
        movie.setGenre(movieRequest.getGenre());
        movie.setDuration(movieRequest.getDuration());
        movie.setLanguage(movieRequest.getLanguage());
        movie.setRating(movieRequest.getRating());
        movie.setReleaseDate(simpleDateFormat.parse(movieRequest.getReleaseDate()));
        movie.setTitle(movieRequest.getTitle());

        repository.save(movie);

    }

    @GetMapping("v1/movie")
    public Movie get(@RequestParam(value = "id", required = false) Long id) {

        return repository.findById(id).get();


    }

    @PatchMapping("v1/movie")
    public void patch(@RequestParam(value = "id", required = false) Long id,
                      @RequestBody MovieRequest movieRequest) {

        Movie movie = repository.findById(id).get();
        movie.setRating(movieRequest.getRating());

        repository.save(movie);


    }

//    @PutMapping("v1/movie/{id}")
//    public void put(@PathParam(value = "id") String id,
//                      @RequestBody MovieRequest movieRequest){
//    }

    @DeleteMapping("v1/movie")
    public void delete(@RequestParam(value = "id", required = false) Long id) {

        Movie movie = repository.findById(id).get();
        repository.delete(movie);


    }

}
