package com.web.dacn.repository;

import javax.transaction.Transactional;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.web.dacn.entity.book.CommentBook;
import com.web.dacn.entity.user.User;

@Repository
public interface CommentBookRepository extends JpaRepository<CommentBook, Long> {

	@Query(value = "select * from commentbook order by mod_time desc", nativeQuery = true)
	Page<CommentBook> findAll(Pageable paeable);

	@Query(value = "select * from user u join commentbook c where c.`user_id` = u.id", nativeQuery = true)
	User findByUserId(long userId);

	CommentBook findById(long id);

	@Transactional
	@Modifying
	@Query(value = "delete from commentbook where id=?1 ", nativeQuery = true)
	void deleteById(long id);

	@Query(value = "select * from commentbook c" + " join user u on c.`user_id`=u.`id` "
			+ " join book b on c.`book_id` = b.`id` "
			+ " where b.name like ?1% and u.uname like ?2% ", nativeQuery = true)
	Page<CommentBook> search(String userName, String bookName, Pageable pageable);

	Page<CommentBook> findByBookSlug(String slug, Pageable pageable);

	Page<CommentBook> findByBookSlugOrderByModTimeDesc(String slug, Pageable pageable);

	Page<CommentBook> findByBookSlugAndCommentBookBookIdIsNullOrderByModTimeDesc(String slug, Pageable pageable);

	List<CommentBook> findByCommentBookCommentBooksId(Long commentId);

	Page<CommentBook> findByBookSlugAndCommentBookBookIdIsNullAndStatusOrderByModTimeDesc(String slug,
			Pageable pageable, Integer status);
}
