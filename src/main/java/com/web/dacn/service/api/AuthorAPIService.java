package com.web.dacn.service.api;

import com.web.dacn.dto.user.AuthorDTO;

public interface AuthorAPIService {
	void deleteAllById(Long[] ids);
	AuthorDTO saveAuthorDTO(AuthorDTO dto);
}
