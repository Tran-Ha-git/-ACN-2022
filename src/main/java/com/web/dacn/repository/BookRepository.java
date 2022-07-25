package com.web.dacn.repository;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.web.dacn.entity.book.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	@Query(value = "select * from book where status <> 0 order by mod_time desc", nativeQuery = true)
	Page<Book> findAll(Pageable paeable);

	@Query(value = "select * from book b" + " join book_author ba on b.`id`=ba.`book_id` "
			+ " join `author` a on a.id = ba.`author_id` "
			+ " where b.name like ?1% and a.fullname like ?2%  group by b.id ", nativeQuery = true)
	Page<Book> search(String bookName, String authorName, Pageable pageable);

	Optional<Book> findOneBySlug(String slug);

	Book findById(long id);
	void deleteById(long id);
	
	@Query(value = "SELECT * FROM book b WHERE EXISTS(SELECT * FROM online WHERE book_id = b.id) ORDER BY view DESC LIMIT 10", nativeQuery = true)
	List<Book> findTop10OnlineBook();

	@Query(value = "SELECT * FROM book b WHERE EXISTS(SELECT * FROM audio WHERE book_id = b.id) ORDER BY view DESC LIMIT 10", nativeQuery = true)
	List<Book> findTop10AudioBook();

	@Query(value = "SELECT * FROM book b WHERE name LIKE %:search% AND status =1", nativeQuery = true)
	Page<Book> findBookContainingSearchOrderBySort(String search, Pageable pageable);
	
	Page<Book> findByNameContaining(String search, Pageable pageable);

	// exist audio, online, pdf

	@Query(value = "SELECT * FROM book b WHERE EXISTS(SELECT * FROM audio WHERE book_id = b.id) AND name LIKE %:search% AND status =1", nativeQuery = true)
	Page<Book> findAudioBookContainingSearchOrderBySort(String search, Pageable pageable);

	@Query(value = "SELECT * FROM book b WHERE EXISTS(SELECT * FROM online WHERE book_id = b.id) AND name LIKE %:search% AND status =1", nativeQuery = true)
	Page<Book> findOnlineBookContainingSearchOrderBySort(String search, Pageable pageable);

	@Query(value = "SELECT * FROM book b WHERE EXISTS(SELECT * FROM pdf WHERE book_id = b.id) AND name LIKE %:search% AND status =1", nativeQuery = true)
	Page<Book> findPdfBookContainingSearchOrderBySort(String search, Pageable pageable);
	
	// Filter by category id
	
	@Query(value = "SELECT * FROM book b WHERE EXISTS("
			+ "SELECT * FROM book_bookcategory WHERE book_id = b.id AND category_id = :categoryId) AND name LIKE %:search%", nativeQuery = true)
	Page<Book> findByNameContainingAndCategoryId(String search, Pageable pageable, Long categoryId);

	
	@Query(value = "SELECT * FROM book b WHERE EXISTS("
			+ "SELECT * FROM book_bookcategory WHERE book_id = b.id AND category_id = :categoryId) AND name LIKE %:search% AND status = 1", nativeQuery = true)
	Page<Book> findBookByCategoryIdAndContainingSearchOrderBySort(String search, Pageable pageable, Long categoryId);
	
	@Query(value = "SELECT * FROM book b WHERE EXISTS("
			+ "SELECT * FROM audio WHERE book_id = b.id) AND EXISTS("
			+ "SELECT * FROM book_bookcategory WHERE book_id = b.id AND category_id = :categoryId) AND "
			+ "name LIKE %:search%", nativeQuery = true)
	Page<Book> findAudioBookByCategoryIdAndContainingSearchOrderBySort(String search, Pageable pageable, Long categoryId);

	@Query(value = "SELECT * FROM book b WHERE EXISTS("
			+ "SELECT * FROM online WHERE book_id = b.id) AND EXISTS("
			+ "SELECT * FROM book_bookcategory WHERE book_id = b.id AND category_id = :categoryId) AND "
			+ "name LIKE %:search%", nativeQuery = true)
	Page<Book> findOnlineBookByCategoryIdAndContainingSearchOrderBySort(String search, Pageable pageable, Long categoryId);

	@Query(value = "SELECT * FROM book b WHERE EXISTS("
			+ "SELECT * FROM pdf WHERE book_id = b.id) AND EXISTS("
			+ "SELECT * FROM book_bookcategory WHERE book_id = b.id AND category_id = :categoryId) AND name LIKE %:search%", nativeQuery = true)
	Page<Book> findPdfBookByCategoryIdAndContainingSearchOrderBySort(String search, Pageable pageable, Long categoryId);

	List<Book> findByCategoriesIdOrderByViewDesc(Long id);

	List<Book> findByAuthorsIdOrderByViewDesc(Long id);

	Page<Book> findByFavoriteBooks_UserIdAndNameContainingIgnoreCaseAndStatus(Long id, String search,
			 int status, Pageable pageable);

	List<Book> findByCategoriesIdAndStatusOrderByViewDesc(Long id, int status);

	List<Book> findByAuthorsIdAndStatusOrderByViewDesc(Long id, int status);
}
