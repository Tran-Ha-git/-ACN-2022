package com.web.dacn.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFile {
	// Upload file, xuat ra duong dan
	public String upload(MultipartFile file, HttpServletRequest req, String targetFolderPath) throws IOException {

		String resourcesFolderPath = "src/main/resources/static";

		byte[] bytes = file.getBytes();

		// File not empty and size > 0KB
		if (!file.isEmpty()) {
			String fileLocation = req.getLocalName() + resourcesFolderPath + targetFolderPath;
			File dir = new File(fileLocation);
			if (!dir.exists())
				dir.mkdirs();

			File serverFile = new File(req.getContextPath() + resourcesFolderPath + targetFolderPath + File.separator
					+ UUID.randomUUID() + file.getOriginalFilename());

			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
			return targetFolderPath + serverFile.getName();

		} else {
			String message = "File is empty";
			return message;
		}

	}

}
