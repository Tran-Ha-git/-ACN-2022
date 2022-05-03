package com.web.dacn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.dacn.entity.OnlineEntity;


public interface OnlineRepository extends JpaRepository<OnlineEntity, Integer> {
	@Query(value="select * from `online` where book_id=?1",nativeQuery = true)
	List<OnlineEntity> findByBookId(int bookId);

}
