package com.web.dacn.service.api;

import java.util.List;
import java.util.Optional;

import com.web.dacn.dto.user.AuthorDTO;
import com.web.dacn.entity.user.Author;

public interface AuthorAPIService {
	void deleteAllById(Long[] ids);
	AuthorDTO saveAuthorDTO(AuthorDTO dto);
	AuthorDTO updateAuthorDTO(AuthorDTO dto);
	Optional<Author> findById(Long id);
	boolean isExistsBySlug(String slug);
	boolean isExistsByIdNotAndSlug(Long id, String slug);
}
