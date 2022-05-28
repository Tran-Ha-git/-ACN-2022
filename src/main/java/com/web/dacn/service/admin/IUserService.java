package com.web.dacn.service.admin;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.web.dacn.dto.user.UserDto;
import com.web.dacn.entity.book.Book;
import com.web.dacn.entity.user.Role;
import com.web.dacn.entity.user.User;

@Service
public interface IUserService {
    
	Page<UserDto> getUsersByPage(int page, long role_id);
    List<Role>  getRoles(long userId);
    User deleteUser(long id);
    UserDto findUserById(long id);
    User findById(long id);
    Role findRoleById(long id);
    Page<UserDto> search(String input, int page);
    
    User save(User entity);
}
