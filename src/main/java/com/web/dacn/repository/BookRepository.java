package com.web.dacn.repository;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.web.dacn.entity.BookEntity;
import com.web.dacn.entity.book.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	@Query(value="select * from book where status <> 0 order by mod_time desc",nativeQuery = true)
	Page<Book> findAll(Pageable paeable);

	//Get book's author (1 book has many authors)
	@Query(value = "select a.fullname "
			+ "from book b "
			+ "join book_author ba on b.`id`=ba.`book_id`"
			+ "join `author` a on a.id = ba.`author_id` where b.id=?1 ",nativeQuery = true)
   List<String> getAuthorsOfBook(long id);

	
	//Get book's category 
	@Query(value = "select c.name from book b "
			+ "join book_bookcategory bc on b.id = bcbook_id" + 
			" join bookcategory c on c.id=bc.`category_id`"
			+ "where b.id=?1",nativeQuery = true)
	List<String> getCategoriesOfBook(long id);
	
	
	@Query(value = "select * from book b"
			+ " join book_author ba on b.`id`=ba.`book_id` "
			+ " join `author` a on a.id = ba.`author_id` "
			+ " where b.name like ?1% and a.fullname like ?2%  group by b.id "
			, nativeQuery = true)
	Page<Book> search(String bookName, String authorName, Pageable pageable);

}