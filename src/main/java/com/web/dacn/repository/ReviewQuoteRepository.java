package com.web.dacn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.dacn.entity.quote.ReviewQuote;

@Repository
public interface ReviewQuoteRepository extends JpaRepository<ReviewQuote, Long>{

}
