package com.gfg.lec5rest.service;

import com.gfg.lec5rest.entity.Cast;
import com.gfg.lec5rest.entity.Movie;
import com.gfg.lec5rest.entity.Review;
import com.gfg.lec5rest.entity.StreamingDetails;
import com.gfg.lec5rest.model.*;
import com.gfg.lec5rest.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void create(MovieRequest movieRequest) {

        Movie movie = Movie.builder()
                .duration(movieRequest.getDuration())
                .genre(movieRequest.getGenre())
                .language(movieRequest.getLanguage())
                .title(movieRequest.getTitle())
                .casts(buildCast(movieRequest))
                .streamingDetails(buildStreamingDetails(movieRequest))
                .build();

        movie.getStreamingDetails().setMovie(movie);

        movieRepository.save(movie);

    }

    private StreamingDetails buildStreamingDetails(MovieRequest movieRequest) {
        return StreamingDetails.builder()
                .downloadLink(movieRequest.getStreamingDetails().getDownloadLink())
                .build();
    }

    private Set<Cast> buildCast(MovieRequest movieRequest) {
        Set<Cast> casts = new HashSet<>();

        for(CastRequest castRequest : movieRequest.getCasts()){
            Cast cast = Cast.builder()
                    .name(castRequest.getName()).build();
            casts.add(cast);
        }

        return casts;
    }

    @Override
    public MovieResponse get(Long id) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("id is not present : "+id));

        return MovieResponse.builder()
                .duration(movie.getDuration())
                .genre(movie.getGenre())
                .language(movie.getLanguage())
                .title(movie.getTitle())
                .casts(buildCast(movie))
                .streamingDetails(buildStreamingDetails(movie))
                .build();

    }

    private StreamingDetailsResponse buildStreamingDetails(Movie movie) {
        return StreamingDetailsResponse.builder()
                .downloadLink(movie.getStreamingDetails().getDownloadLink())
                .build();
    }

    private List<CastResponse> buildCast(Movie movie) {
        List<CastResponse> casts = new ArrayList<>();

        for(Cast cast : movie.getCasts()){
            CastResponse castResponse = CastResponse
                    .builder()
                    .name(cast.getName())
                    .build();
            casts.add(castResponse);
        }

        return casts;
    }



    @Override
    public void update(MovieRequest movieRequest, Long id) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("id is not present : "+id));

        if(movieRequest.getReviewRequests()!=null && !movieRequest.getReviewRequests().isEmpty()){

            movie.setReviews(buildReviews(movieRequest));
        }

        for(Review review : movie.getReviews()){
            review.setMovies(movie);
        }

        movieRepository.save(movie);

    }

    private List<Review> buildReviews(MovieRequest movieRequest) {

        List<Review> reviews = new ArrayList<>();

        for(ReviewRequest reviewRequest : movieRequest.getReviewRequests()){
            Review review = Review.builder()
                    .comment(reviewRequest.getComment())
                    .rating(reviewRequest.getRating())
                    .userId(reviewRequest.getUserId())
                    .build();
            reviews.add(review);
        }
        return reviews;
    }


    @Override
    public void delete(Long id) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("id is not present : "+id));

        movieRepository.delete(movie);

    }
}
