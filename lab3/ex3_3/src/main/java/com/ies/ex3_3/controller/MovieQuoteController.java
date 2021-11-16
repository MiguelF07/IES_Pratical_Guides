package com.ies.ex3_3.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ies.ex3_3.exception.ResourceNotFoundException;
import com.ies.ex3_3.model.Movie;
import com.ies.ex3_3.model.Quote;
import com.ies.ex3_3.service.MovieQuoteService;

@RestController
@RequestMapping("/api/v1")
public class MovieQuoteController {
    @Autowired
    private MovieQuoteService service;

    @GetMapping("/movies") //All movies resources are fetched
    public List<Movie> getAllMovies() {
        return service.getMovies();
    }

    @GetMapping("/movies/{id}") //One Movie resource is fetched
    public ResponseEntity<Movie> getMovieById(@PathVariable(value = "id") Long movieId)
        throws ResourceNotFoundException {
        Movie movie = service.getMovieById(movieId);
        if(movie==null)
        {
            throw new ResourceNotFoundException("Movie not found for this id :: " + movieId);
        }
        return ResponseEntity.ok().body(movie);
    }

    @GetMapping("/movies/random") //One random Movie resource is fetched
    public ResponseEntity<Movie> getMovieRandomly()
        throws ResourceNotFoundException {
            Movie movie = service.getRandMovie();
            if(movie==null)
            {
                throw new ResourceNotFoundException("Movie not found.");
            }
            return ResponseEntity.ok().body(movie);
        }
    
    @PostMapping("/movies") //A new movie resource is created
    public Movie createMovie(@Valid @RequestBody Movie movie) {
        return service.saveMovie(movie);
    }

    @PutMapping("/movies/{id}") //Movie resource is updated
    public ResponseEntity<Movie> updateMovie(@PathVariable(value = "id") Long movieId,
         @Valid @RequestBody Movie movieDetails) throws ResourceNotFoundException {
        Movie movie = service.getMovieById(movieId);
        if(movie==null)
        {
            throw new ResourceNotFoundException("Movie not found for this id :: " + movieId);
        }
        movieDetails.setId(movieId);
        return ResponseEntity.ok(service.updateMovie(movieDetails));
    }

    @DeleteMapping("/movies/{id}") //Movie resource is deleted
    public Map<String, Boolean> deleteMovie(@PathVariable(value = "id") Long movieId)
        throws ResourceNotFoundException {
            Movie movie = service.getMovieById(movieId);
            if(movie==null)
            {
                throw new ResourceNotFoundException("Movie not found for this id :: " + movieId);
            }

        service.deleteMovie(movieId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    //For the quotes
    @GetMapping("/quotes") //All quotes resources are fetched
    public List<Quote> getAllQuotes() {
        return service.getQuotes();
    }

    @GetMapping("/quotes/{id}") //One quote resource is fetched
    public ResponseEntity<Quote> getQuoteById(@PathVariable(value = "id") Long quoteId)
        throws ResourceNotFoundException {
        Quote quote = service.getQuoteById(quoteId);
        if(quote==null)
        {
            throw new ResourceNotFoundException("Movie not found for this id :: " + quoteId);
        }
        return ResponseEntity.ok().body(quote);
    }

    @PostMapping("/quotes") //A new quote resource is created
    public Quote createQuote(@Valid @RequestBody Quote quote) {
        return service.saveQuote(quote);
    }

    @GetMapping("/quotes/random") //One random Movie resource is fetched
    public ResponseEntity<Quote> getQuoteRandomly()
        throws ResourceNotFoundException {
            Quote quote = service.getRandQuote();
            if(quote==null)
            {
                throw new ResourceNotFoundException("Quote not found.");
            }
            return ResponseEntity.ok().body(quote);
        }

    @GetMapping("/movies/{id}/quotes/random") //One random Movie resource is fetched
    public ResponseEntity<Quote> getQuoteRandomlyForMovie(@PathVariable(value = "id") Long movieId)
        throws ResourceNotFoundException {
            Quote quote = service.getRandQuoteForMovie(movieId);
            if(quote==null)
            {
                throw new ResourceNotFoundException("Quote not found.");
            }
            return ResponseEntity.ok().body(quote);
        }

    @PutMapping("/quotes/{id}") //quote resource is updated
    public ResponseEntity<Quote> updateQuote(@PathVariable(value = "id") Long quoteId,
         @Valid @RequestBody Quote quoteDetails) throws ResourceNotFoundException {
        Quote quote = service.getQuoteById(quoteId);
        if(quote==null)
        {
            throw new ResourceNotFoundException("Quote not found for this id :: " + quoteId);
        }
        quoteDetails.setId(quoteId);
        return ResponseEntity.ok(service.updateQuote(quoteDetails));
    }

    @DeleteMapping("/quotes/{id}") //Quote resource is deleted
    public Map<String, Boolean> deleteQuote(@PathVariable(value = "id") Long quoteId)
        throws ResourceNotFoundException {
            Quote quote = service.getQuoteById(quoteId);
            if(quote==null)
            {
                throw new ResourceNotFoundException("Quote not found for this id :: " + quoteId);
            }

        service.deleteQuote(quoteId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    
}