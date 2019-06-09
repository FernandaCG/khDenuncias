package com.helc.complain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.helc.complain.entity.Center;
import com.helc.complain.entity.Complain;

@Repository
public interface ICenterRepository extends MongoRepository<Center, String>{
	
	public Optional<Center> findById(String id);
	
	public List<Center> findByAsignadoPEMEX(String asignado);
	
	public List<Center> findByAsignadoSEDENA(String asignado);

}
