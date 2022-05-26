package com.web.dacn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.web.dacn.dto.user.AuthorDTO;
import com.web.dacn.entity.user.Author;

@Controller
public class DemoController {
	
	@GetMapping("/pdf-read")
	public String getPdfRead(Model model) {
		return "pdfReadPage";
	}

	@GetMapping("/online-read")
	public String getOnlineRead(Model model) {
		return "onlineReadPage";
	}

	@GetMapping("/auth")
	public String getAuthPage(Model model) {
		return "authPage";
	}

	@GetMapping("/audio")
	public String getAudio(Model model) {
		return "audioPage";
	}

	@GetMapping("/detail")
	public String getDetail(Model model) {
		return "detailBookPage";
	}

	
	@GetMapping("/admin/new-book")
	public String getAdminNewBook(Model model) {
		return "adminNewBook";
	}
	
	@GetMapping("/404")
	public String get404(Model model) {
		return "404";
	}
	
}
