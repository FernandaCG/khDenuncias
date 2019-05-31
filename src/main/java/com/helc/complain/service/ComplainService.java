package com.helc.complain.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helc.complain.entity.Coordenadas;
import com.helc.complain.entity.Complain;
import com.helc.complain.entity.InstitutionResponse;
import com.helc.complain.exception.ComplainsNotFoundException;
import com.helc.complain.repository.IComplainRepository;
import com.helc.complain.util.ComplainState;
import com.helc.complain.util.Constants;

@Service
public class ComplainService implements IComplainService {

	private static final Logger log = LogManager.getLogger(ComplainService.class);

	@Autowired
	private IComplainRepository complainRepository;

	@Override
	@Transactional(readOnly = true)
	public Page<Complain> findAllComplains(Pageable pageable) throws ComplainsNotFoundException {
		Page<Complain> page = complainRepository.findAll(pageable);
		if (!page.isEmpty()) {
			log.info("The complains were found");
			return page;
		} else {
			log.info("There are not registered complains");
			throw new ComplainsNotFoundException();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Complain> findByUserId(String userId) throws ComplainsNotFoundException {
		List<Complain> complainList = complainRepository.findByUserId(userId);
		if (!complainList.isEmpty()) {
			log.info("The complains were found");
			return complainList;
		} else {
			log.info("There are not registered complains");
			throw new ComplainsNotFoundException();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Complain>findBySendDate(String sendDate) throws ComplainsNotFoundException {
		List<Complain> complainList = complainRepository.findBySendDate(sendDate);
		if (!complainList.isEmpty()) {
			log.info("Success");
			return complainList;
		} else {
			log.info("Empty");
			throw new ComplainsNotFoundException();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Complain findById(String id) {
		return complainRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Complain save(Complain complain, String userId, String institutionId) throws MessagingException {
		complain.setSendDate(new Date());
		Map<String, String> parameters = new HashMap<>();
		parameters.put(Constants.COMPLAIN_DESCRIPTION, complain.getDescripcion());
		complain = complainRepository.save(complain);
		return complain;
	}

	@Override
	public Complain save(Complain complain, @Valid InstitutionResponse institutionResponse) {
		institutionResponse.setResponseDate(new Date());
		//complain.setState(ComplainState.E1.getIdState());
		//complain.setResponse(institutionResponse);
		complain = complainRepository.save(complain);
		return complain;
	}

	@Override
	public List<Double[]> findCoordinates() {
		List<Complain> denuncias = complainRepository.findAll();
		Coordenadas coordenadas=null;
		List<Double[]> listaCoordenadas = new ArrayList();
		GeoJsonPoint g;
		System.out.println("len"+ denuncias.size());
		for (Complain denuncia : denuncias) {			
			g = denuncia.getPunto();
			g.getCoordinates();
			Double[] coord = {g.getX(), g.getY()};
			listaCoordenadas.add(coord);			
		}
		return listaCoordenadas;
	}

	@Override
	@Transactional
	public Complain save(Complain complains) throws MessagingException {
		return complainRepository.save(complains);
	}
	
	

}
