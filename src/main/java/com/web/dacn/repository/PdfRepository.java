package com.web.dacn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.dacn.entity.BookCategoryEntity;
import com.web.dacn.entity.PdfEntity;

 @Repository

public interface PdfRepository extends JpaRepository<PdfEntity, Long> {
	 PdfRepository save(PdfRepository pdfEntity);
	 PdfEntity findById(int id);
}
