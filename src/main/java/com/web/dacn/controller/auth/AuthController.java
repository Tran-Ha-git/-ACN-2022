package com.web.dacn.controller.auth;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.dacn.dto.user.UserDto;
import com.web.dacn.dto.user.UserLoginDto;
import com.web.dacn.entity.user.Role;
import com.web.dacn.entity.user.User;
import com.web.dacn.entity.user.UserRole;
import com.web.dacn.service.auth.RoleService;
import com.web.dacn.service.auth.UserRoleService;
import com.web.dacn.service.auth.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private HttpSession session;

	@GetMapping("login")
	public ModelAndView login(ModelMap model) {
		UserDto user = new UserDto();
		model.addAttribute("user", user);
		return new ModelAndView("authPage", model);
	}

	@PostMapping("login")
	public ModelAndView login(ModelMap model, @Valid @ModelAttribute("user") UserLoginDto userLoginDto,
			BindingResult result) {
//		if (result.hasErrors()) {
//			return new ModelAndView("authPage");
//		}
		User user = userService.login(userLoginDto.getUsername(), userLoginDto.getPassword());
		if (user == null) {
			model.addAttribute("openLogin", true);
			model.addAttribute("user", new UserDto());
			model.addAttribute("userLogin", userLoginDto);
			model.addAttribute("error", "Invalid username or email");
			return new ModelAndView("authPage", model);
		}
		session.setAttribute("user", user);
		for(Role role : user.getRoles()) {
			if(role.getName().equalsIgnoreCase("Admin")) {
				session.setAttribute("ad", true);
				return new ModelAndView("redirect:/admin/books");
			}
		}
		
		
//		Object redirectUri= session.getAttribute("redirect-uri");
//		if(redirectUri!=null) {
//			session.removeAttribute("redirect-uri");
//			return new ModelAndView("redirect:"+redirectUri);
//		}
		return new ModelAndView("redirect:/");
	}

	@PostMapping("signup")
	public ModelAndView signup(ModelMap model, @Valid @ModelAttribute("user") UserDto userDto, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("authPage");
		}
		if(userService.findByEmail(userDto.getEmail().toLowerCase()).isPresent()) {
			result.addError(new FieldError("email", "email", "Email already exists!"));
			return new ModelAndView("authPage");
		}
		if(userService.findByUsernameIgnoreCase(userDto.getUsername()).isPresent()) {
			result.addError(new FieldError("username", "username", "Username already exists!"));
			return new ModelAndView("authPage");
		}
		Optional<Role> optionalRole = roleService.findByNameIgnoreCase("client");
		List<Role> listRoles = new ArrayList<>();
		User user = new User();
		UserRole userRole = new UserRole();
		BeanUtils.copyProperties(userDto, user);
		if (optionalRole.isPresent()) {
			listRoles.add(optionalRole.get());
		}
		user.setStatus(1);
		user.setEmail(user.getEmail().toLowerCase());
		user.setUsername(user.getUsername().toLowerCase());
		user = userService.save(user);
		if (optionalRole.isPresent()) {
			userRole.setRole(optionalRole.get());
			userRole.setUser(user);
			userRoleService.save(userRole);
		}
		session.setAttribute("user", user);
		for(Role role : user.getRoles()) {
			if(role.getName().equalsIgnoreCase("Admin")) {
				session.setAttribute("ad", true);
			}
		}
		return new ModelAndView("redirect:/");
	}

	@RequestMapping("logout")
	public ModelAndView logout(ModelMap model) {
		session.removeAttribute("user");
		session.removeAttribute("ad");
		return new ModelAndView("redirect:/");
	}

}
