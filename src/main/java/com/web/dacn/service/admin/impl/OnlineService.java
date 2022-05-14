package com.web.dacn.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dacn.dto.book.OnlineDTO;
import com.web.dacn.entity.book.Online;
import com.web.dacn.repository.OnlineRepository;
import com.web.dacn.service.admin.IOnlineService;
import com.web.dacn.utils.Converter;
@Service
public class OnlineService implements IOnlineService {
	@Autowired
	private OnlineRepository onlineRepo;
	@Autowired
	private Converter converter;
	public Online save(Online online) {
		return onlineRepo.save(online);
	}
	@Override
	public void updateOnline(long id, Online newOnline) {    
		onlineRepo.save(newOnline);
	}
	
	@Override
	public Online findOnlineById(long id) {
	return onlineRepo.findById(id);
	}
	
	@Override
	public OnlineDTO findOnlineDTOById(long id) {
		Online online= onlineRepo.findById(id);
		OnlineDTO onlineDTO= converter.toDTO(online);
		return onlineDTO;
	}

	@Override
	public boolean deleteOnline(long id) {
		onlineRepo.deleteById(id);
		if (onlineRepo.getById(id) != null) {
			return false;
		}
		return true;
	}

}
