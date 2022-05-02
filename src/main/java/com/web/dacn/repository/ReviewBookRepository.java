package com.web.dacn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewBookRepository extends JpaRepository<ReviewBookRepository, Long> {

}
