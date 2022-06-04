package com.web.dacn.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.web.dacn.entity.book.Audio;
@Repository
public interface AudioRepository extends JpaRepository<Audio, Long> {
	@Query(value="select * from audio where book_id=?1",nativeQuery = true)
	List<Audio> findByBookId(long bookId);
	
	boolean existsByBookId(long bookId);
	
	List<Audio> findByBookIdOrderByPriorityAsc(Long bookId);
	Audio findById(long id);
	
	
	
	@Transactional
	@Modifying	
	void deleteById(long id);

	Audio findByBook_SlugAndPriority(String bookSlug, Integer priority);

	Audio findByBook_SlugAndName(String bookSlug, String name);


	
}

