package com.ies.ex3_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ies.ex3_3.model.Quote;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long>{

}