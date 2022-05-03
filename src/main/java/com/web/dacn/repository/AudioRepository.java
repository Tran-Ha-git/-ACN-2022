package com.web.dacn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.dacn.entity.AudioEntity;
import com.web.dacn.entity.book.Audio;

public interface AudioRepository extends JpaRepository<Audio, Long> {

	List<Audio> findByBookId(long bookId);

}

