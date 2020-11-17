package com.gfg.lec5rest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.lec5rest.entity.*;
import com.gfg.lec5rest.model.*;
import com.gfg.lec5rest.repository.MovieRepository;
import com.gfg.lec5rest.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private RedisRepository ratingRepository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    ObjectMapper objectMapper = new ObjectMapper();

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
    //Inserting review in the mysql
    //Inserting cumulative review in the cache
    public void update(MovieRequest movieRequest, Long id) throws JsonProcessingException {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("id is not present : "+id));
        List<Review> movieReview = new ArrayList<>();

        if(movieRequest.getReviewRequests()!=null && !movieRequest.getReviewRequests().isEmpty()){
            movieReview=buildReviews(movieRequest,movie);
            movie.setReviews(movieReview);
        }

        for(Review review : movie.getReviews()){
            review.setMovies(movie);
        }
        movieRepository.save(movie);

        Rating rating = Rating
                .builder()
                .id(movie.getId())
                .rating(getCumulativeRating(movieReview))
                .build();
        //Produce rating in kafka
        //ratingRepository.save(rating);
        kafkaTemplate.send("rating","key1",objectMapper.writeValueAsString(rating));
    }



    @Override
    public Float getReview(Long id){
        //
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("id is not present : "+id));
        return rating.getRating();
    }

    private List<Review> buildReviews(MovieRequest movieRequest, Movie movie) {

        List<Review> reviews = Objects.isNull(movie.getReviews()) ? new ArrayList<>() : movie.getReviews();

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

    private Float getCumulativeRating(List<Review> reviews){
        if(reviews.isEmpty()){
            return (float) 0;
        }

        Float rating = (float)0;
        for(Review review : reviews){
            rating = rating + (float)review.getRating();
        }
        return rating/reviews.size();

    }

    @Override
    public void delete(Long id) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("id is not present : "+id));

        movieRepository.delete(movie);

    }
}
