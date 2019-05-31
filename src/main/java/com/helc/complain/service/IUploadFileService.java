package com.helc.complain.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.helc.complain.entity.Photo;

public interface IUploadFileService {

	public Resource upload(String fileName) throws MalformedURLException;

	public String copy(MultipartFile file) throws IOException;

	public boolean delete(String fileName) throws IOException;

	public Path getPath(String fileName);

	public Photo getImage(byte[] bytes);

	public String getImageName(MultipartFile file);
}
