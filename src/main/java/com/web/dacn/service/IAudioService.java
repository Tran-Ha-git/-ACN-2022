package com.web.dacn.service;

import java.util.List;

import com.web.dacn.dto.AudioDto;
import com.web.dacn.dto.BookDto;
import com.web.dacn.entity.Book;

public interface IAudioService {
	AudioDto getAudioById(Long id);
	
	AudioDto getFirstAudioById(Long id);
	
	List<AudioDto> getAllAudioById(Long id);
	
	List<BookDto> getAudioBooks(Integer pageNo, Integer pageSize, String sortBy);
}
