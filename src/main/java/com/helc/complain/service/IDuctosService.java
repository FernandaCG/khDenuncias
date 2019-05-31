package com.helc.complain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.helc.complain.entity.Ductos;
import com.helc.complain.exception.DuctosNotFoundException;

public interface IDuctosService {
	
	public Page<Ductos> findAllDuctos(Pageable pageable) throws DuctosNotFoundException;

}
