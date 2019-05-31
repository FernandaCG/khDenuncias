package com.helc.complain.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.helc.complain.entity.Photo;
import com.helc.complain.util.Constants;

@Service
public class UploadFileService implements IUploadFileService {

	private static final Logger log = LogManager.getLogger(UploadFileService.class);

	/**
	 * 
	 */
	@Override
	public Resource upload(String fileName) throws MalformedURLException {
		Path pathFile = getPath(fileName);
		Resource resource = new UrlResource(pathFile.toUri());

		if (!resource.exists() && !resource.isReadable()) {
			pathFile = Paths.get("src/main/resources/static/img").resolve("no-evidence.jpg").toAbsolutePath();
			resource = new UrlResource(pathFile.toUri());
		}
		return resource;
	}

	/**
	 * 
	 */
	@Override
	public String copy(MultipartFile file) throws IOException {
		String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "");
		Path filePath = getPath(fileName);
		Files.copy(file.getInputStream(), filePath);

		return fileName;
	}

	/**
	 * 
	 */
	@Override
	public boolean delete(String fileName) throws IOException {
		if (fileName != null && fileName.length() > 0) {
			Path lastFilePath = Paths.get(Constants.UPLOADS).resolve(fileName).toAbsolutePath();
			File lastPhotoFile = lastFilePath.toFile();
			if (lastPhotoFile.exists() && lastPhotoFile.canRead()) {
				Files.delete(lastFilePath);
				return true;
			}
		} else {
			log.error(Constants.ERROR, fileName + "does not exists");
		}
		return false;
	}

	/**
	 * 
	 */
	@Override
	public Path getPath(String fileName) {
		return Paths.get(Constants.UPLOADS).resolve(fileName).toAbsolutePath();
	}

	/**
	 * 
	 */
	@Override
	public Photo getImage(byte[] bytes) {
		Photo photo = new Photo();
		photo.setPhoto(new Binary(BsonBinarySubType.BINARY, bytes));
		return photo;
	}

	/**
	 * 
	 */
	@Override
	public String getImageName(MultipartFile file) {
		return UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "");
	}

}
