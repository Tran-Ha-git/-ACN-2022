package com.web.dacn.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.web.dacn.dto.AudioDto;
import com.web.dacn.dto.BookDto;
import com.web.dacn.entity.Audio;
import com.web.dacn.entity.Book;
import com.web.dacn.repository.AudioRepository;
import com.web.dacn.service.IAudioService;
import com.web.dacn.utils.AudioConverter;

@Service
public class AudioService implements IAudioService {

	@Autowired
	private AudioRepository audioRepository;
	
	@Autowired
	private AudioConverter audioConverter;

	@Override
	public AudioDto getFirstAudioById(Long book_id) {
		Audio audio = audioRepository.findByBook_idAndPriority(book_id,1);
		return audioConverter.convertToDto(audio);
	}

	@Override
	public List<AudioDto> getAllAudioById(Long book_id) {
		List<AudioDto> results = new ArrayList<AudioDto>();
		List<Audio> audios = audioRepository.findDistinctByBook_idAndPriorityNot(book_id,1,Sort.by(Sort.Direction.ASC, "priority"));
		for (Audio audio : audios) {
			AudioDto dto = audioConverter.convertToDto(audio);
			results.add(dto);
		}
		return results;
	}

	@Override
	public List<BookDto> getAudioBooks(Integer pageNo, Integer pageSize, String sortBy) {
		List<BookDto> results = new ArrayList<BookDto>();
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return null;
	}

	@Override
	public AudioDto getAudioById(Long id) {
		Audio audio = audioRepository.findById(id).orElse(null);
		return (audio!=null?audioConverter.convertToDto(audio): new AudioDto());
	}

	
	
}
