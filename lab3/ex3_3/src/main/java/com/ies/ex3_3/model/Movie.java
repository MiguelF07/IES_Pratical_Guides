package com.ies.ex3_3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity //An entity represents a table stored in a database
@Table(name = "movies") //The table is named movies
public class Movie {


    private long id;
    private String title;
    private int year;
    //private List<Quote> quotes = new ArrayList<Quote>();

    public Movie() {

    }

    public Movie(String title, int year) {
        this.title = title;
        this.year = year;
    }

    @Id //The ID will be auto generated
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }


    @Column(name = "title", nullable = false) //Describing each column
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "year", nullable = false)
    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // //@Column(name = "quotes", nullable = false)
    // @OneToMany(
    //     mappedBy = "movie",
    //     cascade = CascadeType.ALL,
    //     orphanRemoval = true
    // )
    // public List<Quote> getQuotes() {
    //     return this.quotes;
    // }

    // public void setQuotes(List<Quote> quotes) {
    //     this.quotes = quotes;
    // }


    @Override
    public String toString() {
        return "Movie [id=" + id + ", title=" + title + ", year=" + year + "]";
    }

    
}
