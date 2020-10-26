package com.gfg.lec5rest.resources;

import com.gfg.lec5rest.model.MovieRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@Slf4j
public class MovieController {

    private MovieRequest movieRequest;

    @PostMapping("/v1/movie")
    void create(@RequestBody MovieRequest movieRequest){

        log.info("received movie request for movie {}"
        , movieRequest.getTitle());
        this.movieRequest = movieRequest;

    }

    @GetMapping("v1/movie")
    public MovieRequest get(@RequestParam(value = "id",required = false)String id){
        return this.movieRequest;
    }

    @PatchMapping("v1/movie/{id}")
    public void patch(@PathParam(value = "id") String id,
                      @RequestBody MovieRequest movieRequest){
        this.movieRequest.setRating( movieRequest.getRating());
    }

    @PutMapping("v1/movie/{id}")
    public void put(@PathParam(value = "id") String id,
                      @RequestBody MovieRequest movieRequest){
        this.movieRequest = movieRequest;
    }

    @DeleteMapping("v1/movie/{id}")
    public void delete(@PathParam(value = "id") String id){
        this.movieRequest=null;
    }

}
