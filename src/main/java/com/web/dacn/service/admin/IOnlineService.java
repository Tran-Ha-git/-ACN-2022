package com.web.dacn.service.admin;

import org.springframework.stereotype.Service;

import com.web.dacn.dto.book.OnlineDTO;
import com.web.dacn.entity.book.Online;

@Service
public interface IOnlineService {

	public boolean deleteOnline(long id);

	Online save(Online online);

	void updateOnline(long id, Online online);

	Online findOnlineById(long id);

	OnlineDTO findOnlineDTOById(long id);
}
