package com.helc.complain.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.helc.complain.entity.Complain;

@Repository
public interface IComplainRepository extends MongoRepository<Complain, String> {
	
	public List<Complain> findByEmail(String email);
	public List<Complain> findBySendDate(String sendDate);
}
