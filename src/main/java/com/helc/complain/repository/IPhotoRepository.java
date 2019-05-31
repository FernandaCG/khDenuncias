package com.helc.complain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.helc.complain.entity.Photo;

@Repository
public interface IPhotoRepository extends MongoRepository<Photo, String> {

	public Photo findByPhotoName(String name);

}
