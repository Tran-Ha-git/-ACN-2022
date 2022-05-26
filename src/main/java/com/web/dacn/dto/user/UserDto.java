package com.web.dacn.dto.user;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	@NotEmpty
	@NotBlank
	@NotNull
	@Length(min = 3, max = 30)
	private String username;
	@NotEmpty
	@NotNull
	@Length(min = 6, max = 50)
	private String password;
	@NotEmpty
	private String fullname;
	@NotEmpty
	@NotNull
	@Email
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	@NotEmpty
	private String address;
	private String phone;
	private Integer status;
}
