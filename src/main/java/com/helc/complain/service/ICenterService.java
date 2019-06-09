package com.helc.complain.service;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.helc.complain.entity.Center;
import com.helc.complain.exception.CenterNotFoundException;

public interface ICenterService {
	
	public Page<Center> findAllCenters(Pageable pageable) throws CenterNotFoundException;
	public List<Center> findAllActuales() throws CenterNotFoundException;
	public List<Double[]> findCoordinates();
	public Center save(Center center) throws MessagingException;
	public Optional<Center> findById(String id);
	public List<Center> findByAsignadoPEMEX(String asignado);
	public List<Center> findByAsignadoSEDENA(String asignado);
	public void delete(String id);
	public void comparar(Center centro) throws CenterNotFoundException;
}
