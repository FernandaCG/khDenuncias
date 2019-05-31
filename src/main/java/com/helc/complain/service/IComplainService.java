package com.helc.complain.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.helc.complain.entity.Complain;
import com.helc.complain.entity.InstitutionResponse;
import com.helc.complain.exception.ComplainsNotFoundException;

public interface IComplainService {

	public Page<Complain> findAllComplains(Pageable pageable) throws ComplainsNotFoundException;

	public List<Complain> findByUserId(String userId) throws ComplainsNotFoundException;

	public List<Complain> findBySendDate(String sendDate) throws ComplainsNotFoundException;

	public Complain findById(String id);
	
	public Complain save(Complain complains) throws MessagingException;

	public Complain save(Complain complains, String idUser, String idInstitution) throws MessagingException;

	public Complain save(Complain complain, @Valid InstitutionResponse institutionResponse);
	
	public List<Double[]> findCoordinates();

}
