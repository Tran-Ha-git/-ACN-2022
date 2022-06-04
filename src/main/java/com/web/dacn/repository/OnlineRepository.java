package com.web.dacn.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.web.dacn.entity.book.Online;

@Repository
public interface OnlineRepository extends JpaRepository<Online, Long>{
	List<Online> findByBookId(long  bookId);
	Online findById(long id);
	boolean existsByBookId(long bookId);	
	List<Online> findByBookIdOrderByPriorityAsc(long bookId, Pageable pageable);

      
	Online save(Online online);

	void deleteByBookId(long id);
	
	@Transactional
	@Modifying	
	@Query(value="delete from online where id=?1 ",nativeQuery = true)
	void deleteById(long id);
	
	Online findByBook_SlugAndName(String bookSlug, String chapterName);
}
