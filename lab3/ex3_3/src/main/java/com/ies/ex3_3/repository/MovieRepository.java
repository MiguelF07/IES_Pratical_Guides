package com.ies.ex3_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ies.ex3_3.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

}