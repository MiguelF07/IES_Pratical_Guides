package com.ies.ex3_3.service;

import com.ies.ex3_3.model.Movie;
import com.ies.ex3_3.model.Quote;
import com.ies.ex3_3.repository.MovieRepository;
import com.ies.ex3_3.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class MovieQuoteService {
    @Autowired
    private MovieRepository movieRep;

    @Autowired
    private QuoteRepository quoteRep;

    //Movie Section

    public Movie saveMovie(Movie movie) {
        return movieRep.save(movie);
    }

    public List<Movie> saveMovies(List<Movie> movies) {
        return movieRep.saveAll(movies);
    }

    public List<Movie> getMovies() { 
        return movieRep.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRep.findById(id).orElse(null);
    }

    public Movie getRandMovie() { //In this function i used this method because there may be ID's with no content. Same for the getRandQuote
        List<Long> listIds= new ArrayList<>();
        for(Movie m:movieRep.findAll())
        {
            listIds.add(m.getId());
        }
        Random r = new Random();
        int option = r.nextInt(listIds.size());
        return movieRep.findById(listIds.get(option)).orElse(null);
    }

    public String deleteMovie(Long id) {
        movieRep.deleteById(id);
        return "Movie removed !! " + id;
    }

    public Movie updateMovie(Movie movie) {
        Movie existingMovie = movieRep.findById(movie.getId()).orElse(null);
        existingMovie.setTitle(movie.getTitle());
        existingMovie.setYear(movie.getYear());
        // existingMovie.setQuotes(movie.getQuotes());
        Movie updated = movieRep.save(existingMovie); 
        return updated;
    }

    //Quote Section

    public Quote saveQuote(Quote quote) {
        return quoteRep.save(quote);
    }

    public List<Quote> saveQuotes(List<Quote> quotes) {
        return quoteRep.saveAll(quotes);
    }

    public List<Quote> getQuotes() {
        return quoteRep.findAll();
    }

    public Quote getQuoteById(Long id) {
        return quoteRep.findById(id).orElse(null);
    }

    public Quote getRandQuote() {
        List<Long> listIds= new ArrayList<>();
        for(Quote q:quoteRep.findAll())
        {
            listIds.add(q.getId());
        }
        Random r = new Random();
        int option = r.nextInt(listIds.size());
        return quoteRep.findById(listIds.get(option)).orElse(null);
    }

    public Quote getRandQuoteForMovie(Long movieId) {
        List<Long> listQts= new ArrayList<>();
        Optional<Movie> m = movieRep.findById(movieId);
        if(m==null)
        {
            return null;
        }

        for(Quote q:quoteRep.findAll())
        {
            if(q.getMovie().getTitle().equals(m.get().getTitle()))
            {
                listQts.add(q.getId());
            }

        }
        Random r = new Random();
        int option = r.nextInt(listQts.size());
        return quoteRep.findById(listQts.get(option)).orElse(null);
    }

    public String deleteQuote(Long id) {
        quoteRep.deleteById(id);
        return "Quote removed !! " + id;
    }

    public Quote updateQuote(Quote quote) {
        Quote existingQuote = quoteRep.findById(quote.getId()).orElse(null);
        existingQuote.setMovie(quote.getMovie());
        existingQuote.setText(quote.getText());
        Quote updated = quoteRep.save(existingQuote);
        return updated;
    }
}