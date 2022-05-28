package com.web.dacn.utils;

public class ValidateData {
	public static boolean isSlug(String slug) {
		return !slug.contains(" ");
	}
}
