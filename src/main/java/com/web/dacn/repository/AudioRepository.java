package com.web.dacn.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.dacn.entity.Audio;

public interface AudioRepository extends PagingAndSortingRepository<Audio, Long>{

	Audio findByBook_idAndPriority(Long book_id,Integer priority);

	List<Audio> findDistinctByBook_idAndPriorityNot(Long book_id,Integer priority , Sort sort );
//findByAgeOrderByLastnameDesc
	

}
