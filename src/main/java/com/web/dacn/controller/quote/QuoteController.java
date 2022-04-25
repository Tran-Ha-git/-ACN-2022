package com.web.dacn.controller.quote;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("quotes")
public class QuoteController {
	
	
	public ModelAndView list() {
		return new ModelAndView("");
	}
}
