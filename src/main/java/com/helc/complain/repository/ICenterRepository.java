package com.helc.complain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.helc.complain.entity.Center;

@Repository
public interface ICenterRepository extends MongoRepository<Center, String>{

}
