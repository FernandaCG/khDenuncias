package com.helc.complain.service;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.helc.complain.entity.Photo;
import com.helc.complain.exception.PhotoNotFoundException;

public interface IPhotoService {

	public Page<Photo> findAllPhotos(Pageable pageable) throws PhotoNotFoundException;

	public Photo findByPhotoName(String name);

	public Photo save(MultipartFile photoFile) throws IOException;

	public void delete(String id);
}
