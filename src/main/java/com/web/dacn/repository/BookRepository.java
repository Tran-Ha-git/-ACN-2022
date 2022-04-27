package com.web.dacn.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;


import com.web.dacn.entity.BookEntity;


@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
	BookEntity save(BookEntity bookEntity);

	BookEntity deleteById(int id);

//delete book
	@Modifying
	@Query(value = "delete " + " from pdf p " + " where p.`book_id`=?1 ", nativeQuery = true)
	int deleteByIdPdf(int id);

	@Modifying
	@Query(value = "delete " + " from audio a " + " where a.`book_id`=?1 ", nativeQuery = true)
	int deleteByIdAudio(int id);

	@Modifying
	@Query(value = "delete " + " from online o " + " where o.`book_id`=?1 ", nativeQuery = true)
	int deleteByIdOnline(int id);

	@Modifying
	@Query(value = "delete " + " from book_author ba " + " where ba.`book_id`=?1 ", nativeQuery = true)
	int deleteByIdBookAuthor(int id);

	@Modifying
	@Query(value = "delete " + " from book_bookcategory bb " + " where bb.`book_id`=?1 ", nativeQuery = true)
	int deleteByIdBookBookCategory(int id);

	// add book(=query nhung ko cần dùng vì đã sử dụng @manytomany)
//	@Modifying
//	@Query(value = "insert into author( fullname,birthday,`address`,phone,`description`,slug,meta_title,meta_description, `status`, mod_time, mod_user_id) values(?,?,?,?,?,?,?,?,?,?,?)", nativeQuery = true)
//	int addAuthor(String fullname,Date birthday,String address,String phone,String description,String slug,String meta_title,String meta_description,int status,Date mod_time, int mod_user_id);
//    
//	@Modifying
//	@Query(value = "insert into book(`name`,thumbnail,price,vip,`description`,slug, meta_title,meta_description, `status`, mod_time, mod_user_id) values(?,?,?,?,?,?,?,?,?,?,?)", nativeQuery = true)
//	int addBook(String name,String thumbnail ,float price,boolean vip,String description,String slug,String meta_title,String meta_description,int status,Date mod_time, int mod_user_id);
//     
//	@Modifying
//	@Query(value = "insert into book_author(book_id,author_id) values(?,?)", nativeQuery = true)
//	int addBook_Author(int book_id,int author_id);
//	
//	@Modifying
//	@Query(value = "insert into bookcategory(`name`,parent_id,`view`,slug, meta_title,meta_description, `status`) values(?,?,?,?,?,?,?)", nativeQuery = true)
//	int addBookCategory(String name,int parent_id,int view,String slug,String meta_title,String meta_description,int status);
//	
//	@Modifying
//	@Query(value = "insert into bookcategory(`book_id`,category_id) values(?,?)", nativeQuery = true)
//	int addBook_BookCategory(int book_id,int category_id);
//	
// get name category
//	
//	@Query("SELECT c FROM bookcategory c WHERE c.name = :name ")
//	BookDTo findByName(@Param("name") String nameCategory);
//	//get name author
//	@Query("SELECT fullname FROM author  WHERE fullname =?1")
//	String findByNameAuthor( String nameAuthor);
	// Get books
	List<BookEntity> findAll();

	@Query(value = "select * from book where id=?1", nativeQuery = true)
	BookEntity findById(int id);

	// Get some attributes in Book id, hình, tên sách, status, mod_time
//		@Query(value = "select `id`, name, thumbnail, status, mod_time"
//			         + "from book",nativeQuery = true)
//		List<BookEntity> getBooks();

	// Get book's author (1 book has many authors)
	@Query(value = "select a.fullname " + "from book b " + "join book_author ba on b.`id`=ba.`book_id`"
			+ "join `author` a on a.id = ba.`author_id` where b.id=?1 ", nativeQuery = true)
	List<String> getAuthorsOfBook(int id);

	// Get book's category (1 book has many category)
	@Query(value = "select c.name from book b " + "join book_bookcategory bc on b.id = bc.book_id"
			+ " join bookcategory c on c.id=bc.`category_id`" + "where b.id=?1", nativeQuery = true)
	List<String> getCategoriesOfBook(int id);

	// get list url pdf on idbook
	@Query(value = "select p.`url` " + "from book b " + " join pdf p on b.`id`=p.`book_id` "
			+ " where b.id=?1 ", nativeQuery = true)
	List<String> getPdfOfBook(int id);

	// update table book, category, author, pdf
//	@Modifying(clearAutomatically = true)
//	@Transactional
//	@Query(value = "UPDATE book "
//			+ "SET `name`=:name,thumbnail=:thumbnail, price=:price ,vip=:vip,  `description`=description"
//			+ " ,slug=:slug, meta_title=:meta_title, meta_description=:meta_description," + "`status`=:status"
//			+ "WHERE id=:id", nativeQuery = true)



}
