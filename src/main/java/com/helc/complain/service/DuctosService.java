package com.helc.complain.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helc.complain.entity.Ductos;
import com.helc.complain.exception.ComplainsNotFoundException;
import com.helc.complain.exception.DuctosNotFoundException;
import com.helc.complain.repository.IDuctosRepository;

@Service
public class DuctosService implements IDuctosService{
	
	private static final Logger log = LogManager.getLogger(ComplainService.class);
	
	@Autowired
	private IDuctosRepository ductosRepository;
	

	@Override
	@Transactional(readOnly = true)
	public Page<Ductos> findAllDuctos(Pageable pageable) throws DuctosNotFoundException {
		Page<Ductos> page = ductosRepository.findAll(pageable);
		if (!page.isEmpty()) {
			log.info("The complains were found");
			return page;
		} else {
			log.info("There are not registered complains");
			throw new DuctosNotFoundException();
		}
	}

}
