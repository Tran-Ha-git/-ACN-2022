package com.web.dacn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.web.dacn.entity.book.Online;

@Repository
public interface OnlineRepository extends JpaRepository<Online, Long>{
	@Query(value="select * from `online` where book_id=?1",nativeQuery = true)
	List<Online> findByBookId(int bookId);

}
