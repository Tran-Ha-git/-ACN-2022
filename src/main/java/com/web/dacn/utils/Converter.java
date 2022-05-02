package com.web.dacn.utils;
import org.springframework.stereotype.Component;
import com.web.dacn.dto.BookDTO;
import com.web.dacn.entity.BookEntity;

@Component
public class Converter {

	public BookDTO toDTO(BookEntity entity) {
		BookDTO book = new BookDTO(entity.getId(), entity.getName(), entity.getThumbnail(), entity.getModTime());

		// Convert status form int to String to display in UI
		switch (entity.getStatus()) {
		case 0:
			book.setStatus("Đã xóa");
			break;
		case 1:
			book.setStatus("Hoàn thành");
			break;
		case 2:
			book.setStatus("Đang update");
			break;
		default:
			book.setStatus("");
		}

		return book;
	}
}
