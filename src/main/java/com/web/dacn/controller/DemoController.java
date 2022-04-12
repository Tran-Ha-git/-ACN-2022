package com.web.dacn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.dacn.dto.UserDto;
import com.web.dacn.entity.User;
import com.web.dacn.service.IDemoService;

@Controller
@RequestMapping(path = "/demo")
public class DemoController {
	@Autowired
	IDemoService demoService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getDemo(ModelMap modelMap) {
		UserDto u= demoService.getDemo();
		modelMap.addAttribute("name", u.getUname());
		modelMap.addAttribute("stt", 1);
		return "demo";
	}
}
