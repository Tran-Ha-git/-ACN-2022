package com.web.dacn.service.admin.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.web.dacn.dto.book.BookDTO;
import com.web.dacn.dto.quote.QuoteDto;
import com.web.dacn.dto.user.AuthorDTO;
import com.web.dacn.dto.user.UserDto;
import com.web.dacn.entity.book.Book;
import com.web.dacn.entity.quote.Quote;
import com.web.dacn.entity.user.Author;
import com.web.dacn.entity.user.User;
import com.web.dacn.repository.AuthorRepository;
import com.web.dacn.repository.BookRepository;
import com.web.dacn.repository.QuoteRepository;
import com.web.dacn.service.admin.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {
	@Autowired
	private AuthorRepository authorRepository;
	
	@Override
	public Page<AuthorDTO> getListAuthor(String search, String sortStr, int page) {
		PageRequest pageRequest = PageRequest.of(page - 1, 10);
		
		// check sort
		if(sortStr.split("__").length == 2) {
			String[] arrSort = sortStr.split("__");
			
			if(arrSort[1].equals("ASC")) {
				pageRequest = PageRequest.of(page - 1, 10, Sort.by(arrSort[0]).ascending());
			} else {
				pageRequest = PageRequest.of(page - 1, 10, Sort.by(arrSort[0]).descending());
			}			
		}
		
		Page<Author> authorsPageEntity = authorRepository.findByFullnameContainingAndStatus(search, 1, pageRequest);
		
		
		return convertEntityToDTO(authorsPageEntity);
	}

	private Page<AuthorDTO> convertEntityToDTO(Page<Author> page) {
		return page.map(new Function<Author, AuthorDTO>() {
		    @Override
		    public AuthorDTO apply(Author author) {
		    	AuthorDTO authorDTO = new AuthorDTO();
		    	BeanUtils.copyProperties(author, authorDTO);
		    	
		    	User user = author.getUser();
		    	if(user != null) {
			    	UserDto userDTO =  new UserDto();
			    	BeanUtils.copyProperties(author.getUser(), userDTO);
			    	authorDTO.setUser(userDTO);
		    	}
		    		    			    			    	
		    	return authorDTO;
		    }
		});	
	}

	@Override
	public AuthorDTO findById(Long id) {
		Optional<Author> optional = authorRepository.findById(id);
		if(optional.isPresent()) {
			AuthorDTO authorDTO = new AuthorDTO();
			BeanUtils.copyProperties(optional.get(), authorDTO);

			optional.get().getBooks().stream().forEach(entity -> {
				BookDTO bookDTO = new BookDTO();
				BeanUtils.copyProperties(entity, bookDTO);
				authorDTO.getBooks().add(bookDTO);
			});
			
			optional.get().getQuotes().stream().forEach(entity -> {
				QuoteDto quoteDTO = new QuoteDto();
				BeanUtils.copyProperties(entity, quoteDTO);
				authorDTO.getQuotes().add(quoteDTO);
			});
			
			return authorDTO;
		} 
		return null;
	}

	@Override
	public Author findById(long id) {
		return authorRepository.findById(id);
	}

	@Override
	public Page<Author> findByFullnameContaining(String fullname, Pageable page) {
		return authorRepository.findByFullnameContainingAndStatus(fullname, 1, page);
	}

	@Override
	public Author findByFullname(String name) {
		return authorRepository.findByFullname(name);
	}

	@Override
	public Optional<Author> findBySlug(String slug) {
		return authorRepository.findBySlug(slug);
	}

	@Override
	public boolean existsBySlug(String slug) {
		return authorRepository.existsBySlug(slug);
	}

	@Override
	public boolean existsByIdNotAndSlug(Long id, String slug) {
		return authorRepository.existsByIdNotAndSlug(id, slug);
	}

	@Override
	public <S extends Author> S save(S entity) {
		return authorRepository.save(entity);
	}

	@Override
	public <S extends Author> Optional<S> findOne(Example<S> example) {
		return authorRepository.findOne(example);
	}

	@Override
	public List<Author> findAll() {
		return authorRepository.findAll();
	}

	@Override
	public Page<Author> findAll(Pageable pageable) {
		return authorRepository.findAll(pageable);
	}

	@Override
	public List<Author> findAll(Sort sort) {
		return authorRepository.findAll(sort);
	}

	@Override
	public List<Author> findAllById(Iterable<Long> ids) {
		return authorRepository.findAllById(ids);
	}

	@Override
	public <S extends Author> List<S> saveAll(Iterable<S> entities) {
		return authorRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		authorRepository.flush();
	}

	@Override
	public <S extends Author> S saveAndFlush(S entity) {
		return authorRepository.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Long id) {
		return authorRepository.existsById(id);
	}

	@Override
	public <S extends Author> List<S> saveAllAndFlush(Iterable<S> entities) {
		return authorRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Author> Page<S> findAll(Example<S> example, Pageable pageable) {
		return authorRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Author> entities) {
		authorRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Author> long count(Example<S> example) {
		return authorRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Author> entities) {
		authorRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return authorRepository.count();
	}

	@Override
	public <S extends Author> boolean exists(Example<S> example) {
		return authorRepository.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		authorRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		authorRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Author entity) {
		authorRepository.delete(entity);
	}

	@Override
	public <S extends Author, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return authorRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		authorRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		authorRepository.deleteAllInBatch();
	}

	@Override
	public Author getOne(Long id) {
		return authorRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Author> entities) {
		authorRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		authorRepository.deleteAll();
	}

	@Override
	public Author getById(Long id) {
		return authorRepository.getById(id);
	}

	@Override
	public <S extends Author> List<S> findAll(Example<S> example) {
		return authorRepository.findAll(example);
	}

	@Override
	public <S extends Author> List<S> findAll(Example<S> example, Sort sort) {
		return authorRepository.findAll(example, sort);
	}
	
	
}
