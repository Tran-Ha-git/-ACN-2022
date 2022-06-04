package com.web.dacn.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.web.dacn.entity.book.BookMark;

@Repository
public interface BookMarkRepository extends JpaRepository<BookMark, Long>{
    List<BookMark> findByBookId(long id);
    @Modifying
    @Query(value="delete from bookmark where id=?1",nativeQuery = true)
    void deleteById(long id);
    void deleteByBookId(long id);
		
	boolean existsByUser_IdAndBook_SlugAndOnline_Name(Long id, String bookSlug, String chapterName);
	
	long deleteByUser_IdAndBook_SlugAndOnline_Name(Long id, String bookSlug, String chapterName);
	
	Page<BookMark> findAllByUser_IdAndBook_StatusAndBook_NameContainingIgnoreCase(Long id, int i, String search,
			Pageable pageable);
	
	Page<BookMark> findAllByOnline_IdIsNotNullAndUser_IdAndBook_StatusAndBook_NameContainingIgnoreCase(Long id, int i,
			String search, Pageable pageable);
	
	Page<BookMark> findAllByAudio_IdIsNotNullAndUser_IdAndBook_StatusAndBook_NameContainingIgnoreCase(Long id, int i,
			String search, Pageable pageable);
	
	Page<BookMark> findAllByPdf_IdIsNotNullAndUser_IdAndBook_StatusAndBook_NameContainingIgnoreCase(Long id, int i,
			String search, Pageable pageable);
	
	long deleteByUser_IdAndBook_SlugAndOnline_Id(Long id, String bookSlug, Long onlineId);
	
	long  deleteByUser_IdAndBook_SlugAndPdf_Id(Long id, String bookSlug, Long pdfId);
	
	boolean existsByUser_IdAndBook_SlugAndPdf_Id(Long id, String bookSlug, Long pdfId);
	
	long deleteByUser_IdAndBook_SlugAndAudio_Id(Long id, String bookSlug, Long audioId);
	
	boolean existsByUser_IdAndBook_SlugAndAudio_Name(Long id, String bookSlug, String name);
	
	boolean existsByUser_IdAndBook_SlugAndAudio_Id(Long id, String bookSlug, Long audioId);
}
