package com.web.dacn.repository;

import java.util.List;

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
    @Query(value="delete from bookmrk where id=?1",nativeQuery = true)
    void deleteById(long id);
    void deleteByBookId(long id);
}
