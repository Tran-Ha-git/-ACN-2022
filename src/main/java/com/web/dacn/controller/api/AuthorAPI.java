package com.web.dacn.controller.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.dacn.dto.user.AuthorDTO;
import com.web.dacn.service.api.AuthorAPIService;
import com.web.dacn.utils.ResponseObject;
import com.web.dacn.utils.ValidateData;

@RestController
@RequestMapping("/api/authors")
public class AuthorAPI {
	@Autowired
	private AuthorAPIService authorAPIService;

	@DeleteMapping("")
	public ResponseEntity delete(@RequestBody Long[] ids) {
		ResponseObject response = new ResponseObject<>();
		try {
			authorAPIService.deleteAllById(ids);
			response.setMessage("Successful!");
			response.setSuccess(true);
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			ex.printStackTrace();
			response.setMessage("Unsuccessful");
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}

	@PostMapping("")
	public ResponseEntity add(@Valid @RequestBody AuthorDTO dto, BindingResult bindResult) {
		ResponseObject response = new ResponseObject<>();
		if (bindResult.hasErrors()) {
			String message = "";
			for (ObjectError error : bindResult.getAllErrors()) {
				message += error.getDefaultMessage() + ". ";
			}
			response.setMessage(message.trim());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		try {
			if (ValidateData.isSlug(dto.getSlug()) && !authorAPIService.isExistsBySlug(dto.getSlug())) {
				response.setData(authorAPIService.saveAuthorDTO(dto));
				response.setMessage("Successful!");
				response.setSuccess(true);
				return ResponseEntity.ok(response);
			} else {
				response.setMessage("Không đúng định dạng slug hoặc đã tồn tại slug này trong hệ thống");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.setMessage(ex.getMessage());
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}

	@PutMapping("")
	public ResponseEntity update(@Valid @RequestBody AuthorDTO dto, BindingResult bindResult) {
		ResponseObject response = new ResponseObject<>();
		if (bindResult.hasErrors()) {
			String message = "";
			for (ObjectError error : bindResult.getAllErrors()) {
				message += error.getDefaultMessage() + ". ";
			}
			response.setMessage(message.trim());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		try {
			if (dto.getId() == null) {
				response.setMessage("Không tìm thấy ID");
			} else {
				if (authorAPIService.findById(dto.getId()).isPresent()) {
					try {
						if (ValidateData.isSlug(dto.getSlug()) && !authorAPIService.isExistsByIdNotAndSlug(dto.getId(), dto.getSlug())) {
							response.setData(authorAPIService.updateAuthorDTO(dto));
							response.setMessage("Successful!");
							response.setSuccess(true);
							return ResponseEntity.ok(response);

						} else {
							response.setMessage("Không đúng định dạng slug hoặc đã tồn tại slug này trong hệ thống");
							return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
						}
					} catch (Exception ex) {
						ex.printStackTrace();
						response.setMessage(ex.getMessage());
						return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
					}
				} else {
					response.setMessage("Không tồn tại ID này trong hệ thống");
				}
			}
			return ResponseEntity.ok(response);

		} catch (Exception ex) {
			ex.printStackTrace();
			response.setMessage(ex.getMessage());
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
		}
	}
}
