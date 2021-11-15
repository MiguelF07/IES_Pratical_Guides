package com.ies.ex3_3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity 
@Table(name = "quotes") //The table is named quotes
public class Quote {
    private long id;
    
    private Movie movie;

    private String text;

    public Quote() {

    }

    public Quote(Movie movie, String text)
    {
        this.movie = movie;
        this.text = text;
    }

    @Id //The ID will be auto generated
    @GeneratedValue(strategy = GenerationType.AUTO)
        public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }


    //@Column(name = "movie", nullable = false) //Describing each column
    @ManyToOne(fetch = FetchType.LAZY)
    public Movie getMovie() {
        return this.movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Column(name = "text", nullable = false)
    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Quote [id=" + id + ", movie=" + movie.getTitle() + ", text=" + text + "]";
    }

    
}
