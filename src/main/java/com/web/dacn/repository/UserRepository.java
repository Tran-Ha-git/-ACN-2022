package com.web.dacn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.web.dacn.entity.book.Audio;
import com.web.dacn.entity.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByUsernameAndStatus(String username, int status);

	User findByUsername(String username);

	public Optional<User> findByUsernameIgnoreCase(String username);

	public Optional<User> findByEmail(String email);

	User findUserById(long id);
	
	@Query(value = "select * from user u join user_role ur"
			+ " on u.id=ur.user_id where u.status <> 0 and  "
			+ " ur.role_id=?1 ", nativeQuery = true)
	Page<User> findAll(Pageable paeable, long role_id);
	
	@Query(value = "select * from user where status<> 0 and uname =?1 or email=?1 or phone=?1", nativeQuery = true)
	Page<User> findByUsernameOrEmailOrPhone(Pageable pageable, String input);
	
	Page<User> findByPhone(Pageable pageable, String phone);

}