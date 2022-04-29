package com.web.dacn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.dacn.dto.AudioDto;
import com.web.dacn.service.IAudioService;

@RestController
@RequestMapping(value = "/api/audio")
public class AudioApi {
	@Autowired
	private IAudioService audioService;
	
	@GetMapping("/change-chapter")
	public AudioDto changeChapter(@RequestParam Long id) {
		return audioService.getAudioById(id); 
	}
}
