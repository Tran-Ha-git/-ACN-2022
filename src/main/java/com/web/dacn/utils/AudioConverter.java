package com.web.dacn.utils;

import org.springframework.stereotype.Service;

import com.web.dacn.dto.AudioDto;
import com.web.dacn.entity.Audio;

@Service
public class AudioConverter {
	public AudioDto convertToDto(Audio audio) {
		AudioDto result = new AudioDto();
		result.setId(audio.getId());
		result.setName(audio.getName());
		result.setUrl(audio.getUrl());
		result.setPriority(audio.getPriority());
		return result ;
	}
	
	public Audio convertToEntity(AudioDto dto) {
		Audio result = new Audio();
		result.setId(dto.getId());
		result.setName(dto.getName());
		result.setUrl(dto.getUrl());
		result.setPriority(dto.getPriority());
		return result ;
	}
}
