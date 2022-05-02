package com.web.dacn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.dacn.entity.AudioEntity;

public interface AudioRepository extends JpaRepository<AudioEntity, Integer> {
	@Query(value="select * from audio where book_id=?1",nativeQuery = true)
	List<AudioEntity> findByBookId(int bookId);

}

