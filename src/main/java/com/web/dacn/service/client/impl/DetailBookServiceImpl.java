package com.web.dacn.service.client.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.dacn.dto.book.AudioDTO;
import com.web.dacn.dto.book.BookCategoryDTO;
import com.web.dacn.dto.book.BookDTO;
import com.web.dacn.dto.book.CommentBookDTO;
import com.web.dacn.dto.book.OnlineDTO;
import com.web.dacn.dto.book.PdfDTO;
import com.web.dacn.dto.user.AuthorDTO;
import com.web.dacn.dto.user.UserDto;
import com.web.dacn.entity.book.Book;
import com.web.dacn.entity.book.CommentBook;
import com.web.dacn.entity.book.FavoriteBook;
import com.web.dacn.entity.book.ReviewBook;
import com.web.dacn.entity.user.User;
import com.web.dacn.repository.BookRepository;
import com.web.dacn.repository.CommentBookRepository;
import com.web.dacn.repository.FavoriteBookRepository;
import com.web.dacn.repository.ReviewBookRepository;
import com.web.dacn.service.auth.UserService;
import com.web.dacn.service.client.DetailBookService;

@Service
public class DetailBookServiceImpl implements DetailBookService{
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private ReviewBookRepository reviewBookRepository;

	@Autowired
	private CommentBookRepository commentBookRepository;

	@Autowired
	private HttpSession session;

	@Autowired
	private UserService userService;
	
	@Autowired
	private FavoriteBookRepository favoriteBookRepository;

	@Override
	public BookDTO getDetailBookBySlug(String slug) {
		Book book = bookRepository.findOneBySlug(slug).orElse(null);
		return convertBookToDetailBook(book);
	}

	private BookDTO convertBookToDetailBook(Book book) {
		if (book != null) {
			BookDTO result = new BookDTO();
			result.setName(book.getName());
			result.setThumbnail(book.getThumbnail());
			result.setDescription(book.getDescription());
			if(book.getView()==null) {
				result.setView(0);
			}else {
				result.setView(book.getView());
			}
			result.setSlug(book.getSlug());

			book.getAuthors().forEach(author -> {
				AuthorDTO authorDTO = new AuthorDTO();
				authorDTO.setSlug(author.getSlug());
				authorDTO.setFullname(author.getFullname());
				result.getAuthors().add(authorDTO);
			});

			book.getCategories().forEach(cat -> {
				BookCategoryDTO categoryDTO = new BookCategoryDTO();
				categoryDTO.setId(cat.getId());
				categoryDTO.setSlug(cat.getSlug());
				categoryDTO.setName(cat.getName());
				result.getCategories().add(categoryDTO);
			});

			book.getOnlines().forEach(online -> {
				OnlineDTO onlineDTO = new OnlineDTO();
				onlineDTO.setId(online.getId());
				result.getOnlines().add(onlineDTO);
			});

			book.getAudios().forEach(online -> {
				AudioDTO audioDTO = new AudioDTO();
				audioDTO.setId(online.getId());
				result.getAudios().add(audioDTO);
			});
			book.getPdfs().forEach(pdf -> {
				PdfDTO pdfDTO = new PdfDTO();
				pdfDTO.setId(pdf.getId());
				result.getPdfs().add(pdfDTO);
			});
			if(book.getView()==null) {
				book.setView(1);
			}else {
				book.setView(book.getView() + 1);
			}
			bookRepository.save(book);
			return result;
		}
		return null;
	}

	@Override
	public List<BookDTO> getSimilarBooksByBookSlug(String slug, List<BookCategoryDTO> categories,
			List<AuthorDTO> authors) {
		List<BookDTO> results = new ArrayList<BookDTO>();
		List<Long> resultIDs = new ArrayList<>();

		if (categories.size() > 0) {
			categories.forEach(bookCategotyDTO -> {
				List<Book> books = bookRepository.findByCategoriesIdAndStatusOrderByViewDesc(bookCategotyDTO.getId(),1);
				books.forEach(book -> {
					BookDTO bookDTO = convertBookToSimilarBook(book);

					if (!resultIDs.contains(book.getId()) && results.size() < 10) {
						resultIDs.add(book.getId());
						results.add(bookDTO);
					}
					if (results.size() > 10) {
						return;
					}
				});
				if (results.size() > 10) {
					return;
				}
			});
		} else {
			if (authors.size() > 0) {
				authors.forEach(authorDTO -> {
					List<Book> books = bookRepository.findByAuthorsIdAndStatusOrderByViewDesc(authorDTO.getId(),1);
					books.forEach(book -> {
						BookDTO bookDTO = convertBookToSimilarBook(book);

						if (!resultIDs.contains(book.getId()) && results.size() < 10) {
							resultIDs.add(book.getId());
							results.add(bookDTO);
						}
						if (results.size() > 10) {
							return;
						}
					});
					if (results.size() > 10) {
						return;
					}
				});
			}
		}
		return results;
	}

	private BookDTO convertBookToSimilarBook(Book book) {
		BookDTO bookDTO = new BookDTO();
		bookDTO.setId(book.getId());
		bookDTO.setSlug(book.getSlug());
		bookDTO.setName(book.getName());
		bookDTO.setThumbnail(book.getThumbnail());

		book.getCategories().forEach(cat -> {
			BookCategoryDTO categoryDTO = new BookCategoryDTO();
			categoryDTO.setId(cat.getId());
			categoryDTO.setSlug(cat.getSlug());
			categoryDTO.setName(cat.getName());
			bookDTO.getCategories().add(categoryDTO);
		});
		return bookDTO;
	}

	private long totalReviews = 0;

	@Override
	public long getTotalReviews() {
		return this.totalReviews;
	}

	@Override
	public float averageReviewsBook(String slug) {

		List<ReviewBook> reviewBooks = new ArrayList<>();
		reviewBooks = reviewBookRepository.findByBookSlug(slug);

		this.totalReviews = reviewBooks.size();
		float avg = 0;

		if (this.totalReviews > 0) {
			double average = reviewBooks.stream().mapToDouble(ReviewBook::getStar).average().orElse(Double.NaN);

			avg = (float) average;
		}
		return avg != 0 ? (float) (Math.round(avg * 100.0) / 100.0) : 0;
	}

	@Override
	public Page<CommentBookDTO> getCommentsByBookSlug(String slug, int page) {
		int size = 2;
		Pageable pageable = PageRequest.of(page, size);
		Page<CommentBook> results = commentBookRepository
				.findByBookSlugAndCommentBookBookIdIsNullAndStatusOrderByModTimeDesc(slug, pageable, 1);
		if (results.hasContent()) {
			return convertEntityToDTO(results);
		}

		return null;
	}

	private Page<CommentBookDTO> convertEntityToDTO(Page<CommentBook> page) {
		return page.map(new Function<CommentBook, CommentBookDTO>() {
			@Override
			public CommentBookDTO apply(CommentBook entity) {
				CommentBookDTO commentBookDTO = new CommentBookDTO();
				BeanUtils.copyProperties(entity, commentBookDTO);

				UserDto userDTO = new UserDto();
				BeanUtils.copyProperties(entity.getUser(), userDTO);
				commentBookDTO.setUser(userDTO);

				BookDTO bookDTO = new BookDTO();
				BeanUtils.copyProperties(entity.getBook(), bookDTO);
				commentBookDTO.setBook(bookDTO);

				List<CommentBook> listCommentBooks = entity.getCommentBooks();
				List<CommentBookDTO> listCommentBookDTOs = listCommentBooks.stream().map(e -> {
					CommentBookDTO commentBookDTOparent = new CommentBookDTO();
					BeanUtils.copyProperties(e, commentBookDTOparent);
					UserDto userDtoParent = new UserDto();
					userDtoParent.setFullname(e.getUser().getFullname());
					commentBookDTOparent.setUser(userDtoParent);
					return commentBookDTOparent;
				}).collect(Collectors.toList());
				commentBookDTO.setCommentBooks(listCommentBookDTOs);

				ReviewBook reviewBook = reviewBookRepository.findTop1ByBookIdAndUserIdAndModTime(bookDTO.getId(),
						entity.getUser().getId(), entity.getModTime());

				commentBookDTO.setStar(reviewBook != null ? reviewBook.getStar() : 0);
				return commentBookDTO;
			}
		});
	}

	@Override
	public User getLoggedInUser() {
		User user = (User) session.getAttribute("user");
		return user;
	}

	@Transactional
	@Override
	public CommentBookDTO createComment(String slug, int star, String content) {
		User user = userService.findById(getLoggedInUser().getId()).orElseThrow(RuntimeException::new);
		Book book = bookRepository.findOneBySlug(slug).orElse(null);
		if (user == null || book == null)
			return null;

		Date date = new Date();
		CommentBook commentBook = new CommentBook();
		commentBook.setContent(content);
		commentBook.setModTime(date);
		commentBook.setUser(user);
		commentBook.setBook(book);
		commentBook.setStatus(1);

		if (star > 0) {
			ReviewBook reviewBook = reviewBookRepository.findTop1ByBookIdAndUserIdOrderByModTime(book.getId(),
					user.getId());
			if (reviewBook == null) {
				reviewBook = new ReviewBook();
			}
			reviewBook.setModTime(date);
			reviewBook.setBook(book);
			reviewBook.setStar(star);
			reviewBook.setUser(user);
			reviewBook.setContent(content);
			
			reviewBookRepository.save(reviewBook);
		}

		CommentBook newCommentBook = commentBookRepository.save(commentBook);
		CommentBookDTO newCommentBookDTO = new CommentBookDTO();
		BeanUtils.copyProperties(newCommentBook, newCommentBookDTO);

		newCommentBookDTO.setStar(star);
		UserDto userDtoResponse = new UserDto();
		userDtoResponse.setFullname(newCommentBook.getUser().getFullname());
		newCommentBookDTO.setUser(userDtoResponse);

		return newCommentBookDTO;
	}

	@Override
	public boolean hadReviewBook(String slug) {
		if (getLoggedInUser() == null) {
			return false; // can review after log in
		}
		return reviewBookRepository.existsByBook_SlugAndUserId(slug, getLoggedInUser().getId());
	}

	@Override
	public List<CommentBookDTO> getCommentBooksByBookSlug(String slug, Integer page) {
		return getCommentsByBookSlug(slug, page).getContent();
	}
	
	@Transactional
	@Override
	public CommentBookDTO createResponse(String slug, Long commentId, String content) {
		Long userId = getLoggedInUser().getId();
		User user = userService.getById(userId);
		Book book = bookRepository.findOneBySlug(slug).orElse(null);
		if (user == null || book == null)
			return null;

		CommentBook commentBook = new CommentBook();
		commentBook.setContent(content);
		commentBook.setModTime(new Date());
		commentBook.setUser(user);
		commentBook.setBook(book);

		CommentBook parentCommentBook = commentBookRepository.findById(commentId).orElse(null);
		commentBook.setCommentBook(parentCommentBook);

		CommentBook newCommentBook = commentBookRepository.save(commentBook);
		CommentBookDTO commentBookDTO = new CommentBookDTO();
		BeanUtils.copyProperties(newCommentBook, commentBookDTO);

		UserDto userDto = new UserDto();
		userDto.setFullname(user.getFullname());
		commentBookDTO.setUser(userDto);

		return commentBookDTO;
	}

	@Transactional
	@Override
	public Boolean addLovebook(String slug) {
		User user = getLoggedInUser();
		Book book = bookRepository.findOneBySlug(slug).orElse(null);
		if (user == null || book == null)
			return false;
		//user = userService.findById(getLoggedInUser().getId()).orElseThrow(RuntimeException::new);

		FavoriteBook favoriteBook = new FavoriteBook();
		favoriteBook.setBook(book);
		favoriteBook.setUser(user);
		
		if(!favoriteBookRepository.existsByUser_IdAndBook_Slug(user.getId(), slug)) {
			favoriteBookRepository.save(favoriteBook);
		}		
		return true;
	}
	
	@Transactional
	@Override
	public Boolean deleteLovebook(String slug) {
		User user = userService.findById(getLoggedInUser().getId()).orElse(null);
		Book book = bookRepository.findOneBySlug(slug).orElse(null);
		if (user == null || book == null)
			return false;
		if( favoriteBookRepository.deleteByUser_IdAndBook_Slug(user.getId(), slug)>0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Boolean checkLovebook(String slug) {
		User user = getLoggedInUser();
		Book book = bookRepository.findOneBySlug(slug).orElse(null);
		if (user == null || book == null)
			return false;
		return favoriteBookRepository.existsByUser_IdAndBook_Slug(user.getId(), slug);
	}
	
	@Transactional
	@Override
	public Boolean deleteLovebook(Long[] ids) {
		for (Long id : ids) {
			if(favoriteBookRepository.deleteByBookId(id)<0) {
				return false;
			}
		}
		return true;
	}
}
