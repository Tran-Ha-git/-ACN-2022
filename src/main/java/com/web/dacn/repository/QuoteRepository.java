package com.web.dacn.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.dacn.entity.quote.Quote;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long>{
	Page<Quote> findByContentContaining(String content, Pageable pageable);
}
