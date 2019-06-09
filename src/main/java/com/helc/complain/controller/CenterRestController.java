package com.helc.complain.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.hibernate.validator.internal.constraintvalidators.bv.NotNullValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.helc.complain.entity.Center;
import com.helc.complain.entity.Centers;
import com.helc.complain.exception.CenterNotFoundException;
import com.helc.complain.exception.ComplainsNotFoundException;
import com.helc.complain.service.ICenterService;
import com.helc.complain.util.Constants;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/api/v1/user")
public class CenterRestController {

	@Autowired
	private ICenterService centerService;

	@Autowired
	RestTemplate restTemplate;

	private Map<String, Object> response = new HashMap<>();

	@GetMapping("/center/page/{page}")
	public ResponseEntity<?> index(@PathVariable Integer page) {
		try {
			Pageable pageable = PageRequest.of(page, Constants.NUMBER_PAGE);
			return new ResponseEntity<>(centerService.findAllCenters(pageable), HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put(Constants.MESSAGE, Constants.QUERY_ERROR);
			response.put(Constants.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (CenterNotFoundException e) {
			response.put(Constants.MESSAGE, "There are not centers");
			return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/center/coordinates")
	/**
	 * @HystrixCommand(fallbackMethod = "fallback_hello", commandProperties = {
	 * @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
	 *                       value = "9000") })
	 */
	public ResponseEntity<?> getCoordinates() throws CenterNotFoundException {
		try {
			Centers c = restTemplate.getForObject("https://fast-savannah-33025.herokuapp.com/centers", Centers.class);
			for (Center center : c.getComplain_centers()) {
				centerService.comparar(center);
			}
			return new ResponseEntity<>(centerService.findAllActuales(), HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put(Constants.MESSAGE, Constants.QUERY_ERROR);
			response.put(Constants.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/center/{id}")
	public ResponseEntity<?> buscarCentro(@PathVariable String id) {
		try {
			return new ResponseEntity<>(centerService.findById(id), HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put(Constants.MESSAGE, Constants.QUERY_ERROR);
			response.put(Constants.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/center/asignacion")
	public ResponseEntity<?> asignarCentro(@RequestParam("centro") String cntro,
			@RequestParam("asignacion") String asignacion, @RequestParam("dependencia") String dependencia) {
		try {
			Optional<Center> center = centerService.findById(cntro);
			Center centro = center.get();
			Center c = null;
			if (centro.getEstado().equals("Sin asignar")) {
				if (dependencia.equals("1")) {
					centro.setEstado("SEDENA en espera");
					centro.setAsignadoPEMEX(asignacion);
				} else {
					centro.setAsignadoSEDENA(asignacion);
					centro.setEstado("PEMEX en espera");
				}
			} else {
				if (centro.getEstado().contains("espera")) {
					centro.setEstado("En proceso");
					if (dependencia.equals("1")) {
						centro.setAsignadoPEMEX(asignacion);
					} else {
						centro.setAsignadoSEDENA(asignacion);
					}
				}
			}
			try {
				c = centerService.save(centro);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			response.put(Constants.MESSAGE, Constants.SUCCESSFUL_QUERY);
			response.put(Constants.ENTITY, centro);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put(Constants.MESSAGE, Constants.QUERY_ERROR);
			response.put(Constants.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/center/asignacion/finalizar")
	public ResponseEntity<?> finalizarCentro(@RequestParam("centro") String cntro,
			@RequestParam("asignacion") String asignacion, @RequestParam("dependencia") String dependencia) {
		try {
			Optional<Center> center = centerService.findById(cntro);
			Center centro = center.get();
			Center c = null;

			if (centro.getEstado().equals("En proceso")) {
				if (dependencia.equals("1")) {
					centro.setEstado("Finalizado SEDENA");
				} else {
					centro.setEstado("Finalizado PEMEX");
				}
			} else {
				if (centro.getEstado().contains("Finalizado")) {
					centro.setEstado("Terminado");
				}
			}

			try {
				c = centerService.save(centro);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			response.put(Constants.MESSAGE, Constants.SUCCESSFUL_QUERY);
			response.put(Constants.ENTITY, centro);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put(Constants.MESSAGE, Constants.QUERY_ERROR);
			response.put(Constants.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/center/asignadoP/{email}")
	public ResponseEntity<?> buscarAsignadoPEMEX(@PathVariable String email) {
		List<Center> c = centerService.findByAsignadoPEMEX(email);
		List<Center> actuales = new ArrayList();
		for (Center center : c) {
			if (!center.getEstado().equals("Terminado")) {
				actuales.add(center);
			}
		}
		if(actuales.isEmpty()) {
			return new ResponseEntity<>("No tienes incidencias asignadas", HttpStatus.OK);	
		}else {
			return new ResponseEntity<>(actuales, HttpStatus.OK);
		}
		
	}

	@GetMapping("/center/asignadoS/{email}")
	public ResponseEntity<?> buscarAsignadoSEDENA(@PathVariable String email) {
		List<Center> c = centerService.findByAsignadoSEDENA(email);
		List<Center> actuales = new ArrayList();
		for (Center center : c) {
			if (!center.getEstado().equals("Terminado")) {
				actuales.add(center);
			}
		}
		if(actuales.isEmpty()) {
			return new ResponseEntity<>("No tienes incidencias asignadas", HttpStatus.OK);	
		}else {
			return new ResponseEntity<>(actuales, HttpStatus.OK);
		}
	}

	@DeleteMapping("/center/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		response = new HashMap<>();
		try {
			centerService.delete(id);
		} catch (DataAccessException e) {
			response.put(Constants.MESSAGE, Constants.QUERY_ERROR);
			response.put(Constants.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put(Constants.MESSAGE, Constants.SUCCESSFUL_QUERY);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<?> fallback_hello() {
		try {
			Centers c = restTemplate.getForObject("https://kmeans-server-dup.herokuapp.com/centers", Centers.class);
			if (c == null) {
				response.put(Constants.ERROR, "Intente más tarde");
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				for (Center center : c.getComplain_centers()) {
					try {
						centerService.save(center);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}
				return new ResponseEntity<>(c, HttpStatus.OK);
			}
		} catch (DataAccessException e) {
			response.put(Constants.MESSAGE, Constants.QUERY_ERROR);
			response.put(Constants.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
