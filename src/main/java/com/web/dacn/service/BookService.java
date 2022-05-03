//package com.web.dacn.service;
//
//import java.util.List;
//
//import org.springframework.stereotype.Service;
//
//import com.web.dacn.dto.BookDTO;
//
//
//
//@Service
//public interface BookService {
//	boolean deleteBook(int id);
//
//	boolean deleteByIdPdf(int id);
//
//	boolean deleteByIdAudio(int id);
//
//	boolean deleteByIdOnline(int id);
//
//	boolean deleteByIdBookAuthor(int id);
//
//	boolean deleteByIdBookBookCategory(int id);
//	//add book dùng @query
//
////	void addBook(String name, String thumbnail, float price, boolean vip, String description, String slug,
////			String metaTitle, String metaDescription, int status,Date mod_time, int mod_user_id) throws ParseException;
////
////	void addAuthor(String fullname,Date birthday,String address,String phone,String description,String slug,String meta_title,String meta_description,int status,Date mod_time, int mod_user_id);
////
////	void addBookCategory(String name,int parent_id,int view,String slug,String meta_title,String meta_description,int status);
////
////	void addBook_BookCategory(int book_id, int category_id);
////
////	void addBook_Author(int book_id, int author_id);
//
//	List<BookDTO> findAll();
////	boolean findById(int id);
//
////	String findByName(String name);
////
////	String findByFullName(String fullName);
//	boolean checkAvailable(String name );
//	
////    AuthorEntity saveBooKAuthor();
////    BookCategoryEntity saveBooKCategory();
//    
//	List<String> getAuthorsOfBook(int id);
//
//	List<String> getCategoriesOfBook(int id);
//	List<String> getPdfOfBook(int id);
//	List<String> getOnlineOfBook(int id);
//	List<String> getAudioOfBook(int id);
//	
//// chuyển đổi trạng thái từ string về int
//	int convertStatus(String status);
//	BookDTO findById(int id);
//	
//	
//
//	
//
//}