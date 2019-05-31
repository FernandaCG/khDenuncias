package com.helc.complain.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.helc.complain.entity.Photo;
import com.helc.complain.exception.PhotoNotFoundException;
import com.helc.complain.repository.IPhotoRepository;

@Service
public class PhotoService implements IPhotoService {

	private static final Logger log = LoggerFactory.getLogger(PhotoService.class);

	@Autowired
	private IPhotoRepository photoRepository;

	@Autowired
	private IUploadFileService fileService;

	@Override
	public Page<Photo> findAllPhotos(Pageable pageable) throws PhotoNotFoundException {
		Page<Photo> photos = photoRepository.findAll(pageable);
		if (photos.isEmpty()) {
			log.info("There are not registered photos");
			throw new PhotoNotFoundException("There are not registered photos");
		}
		log.info("The photos were found");
		return photoRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Photo findByPhotoName(String name) {
		return photoRepository.findByPhotoName(name);
	}

	@Override
	@Transactional
	public Photo save(MultipartFile photoFile) throws IOException {
		Photo photo = fileService.getImage(photoFile.getBytes());
		photo.setPhotoName(fileService.getImageName(photoFile));
		return photoRepository.save(photo);
	}

	@Override
	@Transactional
	public void delete(String id) {
		photoRepository.deleteById(id);
	}

}
