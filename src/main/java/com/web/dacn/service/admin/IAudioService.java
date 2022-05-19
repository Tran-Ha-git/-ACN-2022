package com.web.dacn.service.admin;

import org.springframework.stereotype.Service;

import com.web.dacn.dto.book.AudioDTO;
import com.web.dacn.entity.book.Audio;
@Service
public interface IAudioService {
  Audio  save(Audio audio);
  void updateAudio(long id,Audio audio);
  Audio findAudioById(long id);
  AudioDTO findAudioDTOById(long id);
  boolean deleteAudio(long id);

}
