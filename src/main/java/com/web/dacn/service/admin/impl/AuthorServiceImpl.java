package com.web.dacn.service.admin.impl;

import java.util.function.Function;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.web.dacn.dto.user.AuthorDTO;
import com.web.dacn.dto.user.UserDto;
import com.web.dacn.entity.user.Author;
import com.web.dacn.entity.user.User;
import com.web.dacn.repository.AuthorRepository;
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
		
		Page<Author> authorsPageEntity = authorRepository.findByFullnameContaining(search, pageRequest);
		
		
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

}
