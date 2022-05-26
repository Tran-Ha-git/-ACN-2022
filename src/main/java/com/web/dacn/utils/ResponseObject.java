package com.web.dacn.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObject<T> {
	private T data = null;
	private String message = "";
	private boolean isSuccess = false;
}
