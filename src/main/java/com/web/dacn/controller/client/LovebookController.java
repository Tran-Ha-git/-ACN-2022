package com.web.dacn.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/lovebook")
public class LovebookController {
	@GetMapping
	public ModelAndView showPage() {
		return new ModelAndView("lovebook");
	}
}
