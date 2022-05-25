package com.web.dacn.service.admin.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.dacn.dto.book.AudioDTO;
import com.web.dacn.entity.book.Audio;
import com.web.dacn.repository.AudioRepository;
import com.web.dacn.service.admin.IAudioService;
import com.web.dacn.utils.Converter;
@Service
public class AudioService implements IAudioService{
    @Autowired 
    private AudioRepository audioRepo;
    @Autowired
    private Converter converter;
	@Override
	public Audio save(Audio audio) {
		return audioRepo.save(audio);
	}
	@Override
	public void updateAudio(long id, Audio newAudio) {    
		audioRepo.save(newAudio);

	}
	
	@Override
	public Audio findAudioById(long id) {
	return audioRepo.findById(id);
	}
	
	@Override
	public AudioDTO findAudioDTOById(long id) {
		Audio audio= audioRepo.findById(id);
		AudioDTO audioDTO= converter.toDTO(audio);
		return audioDTO;
	}

	@Override
	public boolean deleteAudio(long id) {	
		audioRepo.deleteById(id);
		if(audioRepo.getById(id)!=null) {
		return false;
		}
		return true;
	}
	
}
