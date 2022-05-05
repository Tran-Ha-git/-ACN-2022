package com.web.dacn.controller.client;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "/doc-online")
public class ReadOnlineController {
	
	@GetMapping("/{slug}")
	private ModelAndView readOnline(@PathParam(value = "slug") String slug) {
		ModelAndView modelAndView = new ModelAndView("onlineReadPage");
		return modelAndView;
	}
}
