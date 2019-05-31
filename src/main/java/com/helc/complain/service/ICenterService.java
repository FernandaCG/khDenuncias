package com.helc.complain.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.helc.complain.entity.Center;
import com.helc.complain.exception.CenterNotFoundException;

public interface ICenterService {
	
	public Page<Center> findAllCenters(Pageable pageable) throws CenterNotFoundException;
	public List<Double[]> findCoordinates();
}
