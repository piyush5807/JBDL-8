package com.gfg.lec5rest.resources;

import com.gfg.lec5rest.entity.Movie;
import com.gfg.lec5rest.model.MovieRequest;
import com.gfg.lec5rest.model.MovieResponse;
import com.gfg.lec5rest.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@Slf4j
public class MovieController {

    @Autowired
    MovieService movieService;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @PostMapping("/v1/movie")
    void create(@RequestBody MovieRequest movieRequest) throws ParseException {

        movieService.create(movieRequest);

    }

    @GetMapping("v1/movie")
    public MovieResponse get(@RequestParam(value = "id", required = false) Long id) {

        return movieService.get(id);


    }

    @PatchMapping("v1/movie")
    public void patch(@RequestParam(value = "id", required = false) Long id,
                      @RequestBody MovieRequest movieRequest) {

        movieService.update(movieRequest,id);



    }

//    @PutMapping("v1/movie/{id}")
//    public void put(@PathParam(value = "id") String id,
//                      @RequestBody MovieRequest movieRequest){
//    }

    @DeleteMapping("v1/movie")
    public void delete(@RequestParam(value = "id", required = false) Long id) {

        movieService.delete(id);


    }

}
