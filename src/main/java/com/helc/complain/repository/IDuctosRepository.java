package com.helc.complain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.helc.complain.entity.Ductos;

@Repository
public interface IDuctosRepository extends MongoRepository<Ductos, String>{

}
