package com.web.dacn.dto;


import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDto {
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	
	private boolean rememberMe = false;

}

