package com.web.dacn.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.dacn.dto.AudioDto;
import com.web.dacn.dto.BookDto;
import com.web.dacn.service.IAudioService;
import com.web.dacn.service.IBookService;

@Controller
@RequestMapping(path = "/audio")
public class AudioController {
	@Autowired
	private IBookService bookService;
	
	@Autowired
	private IAudioService audioService;
	
	@GetMapping("/list")
	public String getListAudio(Model model,
			@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "15") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
		List<BookDto> books = new ArrayList<BookDto>();
//		books = bookService.getAll(pageNo, pageSize, sortBy);
		books = bookService.getAllBookAudio(pageNo, pageSize, sortBy);
		model.addAttribute("books",books);
		return "listAudioPage";
	}

	@GetMapping("/{id}")
	public String getAudio(@PathVariable Long id, Model model) {
		BookDto book = bookService.getBookById(id);
		AudioDto audio1st = audioService.getFirstAudioById(id);
		List<AudioDto> audios = audioService.getAllAudioById(id);

		model.addAttribute("book", book);
		model.addAttribute("audio1st", audio1st);
		model.addAttribute("audios", audios);
		return "audioPage";
	}
	
}
