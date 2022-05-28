package com.web.dacn.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.dacn.dto.book.BookDTO;
import com.web.dacn.dto.user.UserDto;
import com.web.dacn.entity.user.Role;
import com.web.dacn.entity.user.User;
import com.web.dacn.service.admin.IUserService;

@Controller
@RequestMapping("/admin")
public class UserManagementController {
	@Autowired
	IUserService userService;

	@RequestMapping("users")
	public String getUsers(Model model, HttpSession session, HttpServletRequest req) {
		return getBooksByPage(model, session, 0, req);

	}

	@RequestMapping("/users/{page}")
	public String getBooksByPage(Model model, HttpSession session, @PathVariable("page") int currentpage,
			HttpServletRequest req) {
		String sroleId = req.getParameter("role-id");
		long roleId = 1;
		if (sroleId != null) {
			roleId = Long.parseLong(sroleId);
		}

		Page<UserDto> usersInPage = null;
		if (sroleId == null) {
			usersInPage = userService.getUsersByPage(currentpage, 1);

		}

		usersInPage = userService.getUsersByPage(currentpage, roleId);

		List<UserDto> users = usersInPage.getContent();

		HashMap<Long, List<Role>> listRoles = new HashMap<Long, List<Role>>();

		for (UserDto user : users) {
			listRoles.put(user.getId(), userService.getRoles(user.getId()));

		}
		List<Integer> totalPages = new ArrayList<Integer>();
		for (int i = 0; i < usersInPage.getTotalPages(); i++) {
			totalPages.add(i);
		}

		model.addAttribute("totalPages", totalPages);
		model.addAttribute("total", usersInPage.getTotalElements());
		model.addAttribute("users", users);
		model.addAttribute("roles", listRoles);
		model.addAttribute("currentpage", currentpage);

		return "adminListUser";
	}

	@RequestMapping("/delete-user")
	public String deleteUser(@RequestParam("id") long id, Model model, HttpServletRequest request) {
		userService.deleteUser(id);
		return "redirect:/admin/users";
	}

	@RequestMapping("/delete-all-users")
	public String deleteAllUser(Model model, HttpServletRequest request) {
		String[] deleteItems = request.getParameterValues("delete-item");
		for (String item : deleteItems) {
			long id = Long.parseLong(item);
			userService.deleteUser(id);
		}
		return "redirect:/admin/users";
	}

	@RequestMapping("/edit-user")
	public String editUser(@RequestParam("id") long id, Model model, HttpServletRequest request) {
		UserDto user = userService.findUserById(id);
		List<Role> roles = userService.getRoles(id);

		model.addAttribute("role", roles.get(0));
		model.addAttribute("user", user);
		return "adminEditUser";
	}

	@RequestMapping("/edit-user-role/{id}")
	public String editUserRole(@RequestParam("role") long roleId, @PathVariable("id") long id, Model model,
			HttpServletRequest request) {

		User user = userService.findById(id);
		user.getRoles().removeAll(user.getRoles());

		Role newRole = userService.findRoleById(roleId);
		List<Role> roles = new ArrayList<Role>();
		roles.add(newRole);

		user.setRoles(roles);
		userService.save(user);

		model.addAttribute("role", roles.get(0));
		model.addAttribute("user", user);

		return "redirect:/admin/users?role-id="+roles.get(0).getId();
	}

	@PostMapping("search-users")
	public String search(Model model, HttpSession session, HttpServletRequest req) {
		return searchByPage(model, 0, session, req);
	}

	@RequestMapping("search-users/{page}")
	public String searchByPage(Model model, @PathVariable("page") int currentpage, HttpSession session,
			HttpServletRequest req) {
		String input, message = "";
		Page<UserDto> usersInPage;
		List<UserDto> results = new ArrayList<UserDto>();
		List<Integer> totalPages = new ArrayList<Integer>();
		HashMap<Long, List<Role>> roles = new HashMap<Long, List<Role>>();

		
		if ((currentpage > 0) || (currentpage == 0 && !req.getMethod().equalsIgnoreCase("POST"))) {
			input = (String) session.getAttribute("input");

		} else {

			input = req.getParameter("input");
			session.setAttribute("input", input);

		}

		usersInPage = userService.search(input, currentpage);
		results = usersInPage.getContent();


		for (UserDto user : usersInPage) {
			roles.put(user.getId(), userService.getRoles(user.getId()));

		}
		
		if (results.size() <= 0) {
			message = "No result";
			model.addAttribute("message", message);
		}
		
		for (int i = 0; i < usersInPage.getTotalPages(); i++) {
			totalPages.add(i);
		}

		model.addAttribute("searchPages", totalPages);
		model.addAttribute("roles", roles);
		model.addAttribute("total", usersInPage.getTotalElements());
		model.addAttribute("users", results);
		model.addAttribute("searchInput", input);
		model.addAttribute("message", message);
		return "adminListUser";
	}

}
