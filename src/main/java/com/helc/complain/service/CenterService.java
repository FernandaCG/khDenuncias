package com.helc.complain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helc.complain.entity.Center;
import com.helc.complain.entity.Complain;
import com.helc.complain.entity.Coordenadas;
import com.helc.complain.exception.CenterNotFoundException;
import com.helc.complain.repository.ICenterRepository;

@Service
public class CenterService implements ICenterService {

	private static final Logger log = LogManager.getLogger(ComplainService.class);

	@Autowired
	private ICenterRepository centerRepository;

	@Override
	@Transactional(readOnly = true)
	public Page<Center> findAllCenters(Pageable pageable) throws CenterNotFoundException {
		Page<Center> page = centerRepository.findAll(pageable);
		if (!page.isEmpty()) {
			log.info("The complains were found");
			return page;
		} else {
			log.info("There are not registered complains");
			throw new CenterNotFoundException();
		}
	}

	@Override
	public List<Center> findAllActuales() throws CenterNotFoundException {
		List<Center> centros = centerRepository.findAll();
		List<Center> centrosActuales = new ArrayList();
		if (!centros.isEmpty()) {
			for (Center center : centros) {
				if (!center.getEstado().equals("Terminado")) {
					centrosActuales.add(center);
				}
			}
			log.info("Centros disponibles");
			return centrosActuales;
		} else {
			log.info("No se encontraron centros actualmente");
			return null;
		}
	}

	@Override
	public List<Double[]> findCoordinates() {
		List<Center> centros = centerRepository.findAll();
		Coordenadas coordenadas = null;
		List<Double[]> listaCoordenadas = new ArrayList();
		GeoJsonPoint g;
		System.out.println("len" + centros.size());
		for (Center center : centros) {
			g = center.getCenter();
			g.getCoordinates();
			Double[] coord = { g.getX(), g.getY() };
			listaCoordenadas.add(coord);
		}
		return listaCoordenadas;
	}

	@Override
	public void comparar(Center centro) throws CenterNotFoundException {
		List<Center> actuales = findAllActuales();
		if (actuales == null) {
			try {
				save(centro);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		} else {
			for (Center center : actuales) {
				if (!center.getCenter().equals(centro.getCenter())) {
					try {
						save(center);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	@Transactional
	public Center save(Center center) throws MessagingException {
		return centerRepository.save(center);
	}

	@Override
	public Optional<Center> findById(String id) {
		Optional<Center> c = centerRepository.findById(id);
		return c;
	}

	@Override
	public List<Center> findByAsignadoSEDENA(String asignado) {
		List<Center> c = centerRepository.findByAsignadoSEDENA(asignado);
		return c;
	}

	@Override
	public List<Center> findByAsignadoPEMEX(String asignado) {
		List<Center> c = centerRepository.findByAsignadoPEMEX(asignado);
		return c;
	}

	@Override
	public void delete(String id) {
		centerRepository.deleteById(id);

	}
}
