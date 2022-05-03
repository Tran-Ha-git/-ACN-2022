package com.web.dacn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.dacn.entity.PdfEntity;

 @Repository

public interface PdfRepository extends JpaRepository<PdfEntity, Long> {
	 PdfRepository save(PdfRepository pdfEntity);
	 List<PdfEntity> findByBookId(int id);
}
