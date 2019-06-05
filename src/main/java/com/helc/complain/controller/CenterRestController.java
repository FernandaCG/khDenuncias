package com.helc.complain.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.hibernate.validator.internal.constraintvalidators.bv.NotNullValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.helc.complain.entity.Center;
import com.helc.complain.entity.Centers;
import com.helc.complain.exception.CenterNotFoundException;
import com.helc.complain.exception.ComplainsNotFoundException;
import com.helc.complain.service.ICenterService;
import com.helc.complain.util.Constants;;

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
	public ResponseEntity<?> getCoordinates() {
		try {
			//List <Center> c = (List<Center>) restTemplate.getForObject("https://fast-savannah-33025.herokuapp.com/centers", Center.class);
			//System.out.println("objeto centro: "+ c.toString());
			//restTemplate.exchange("https://fast-savannah-33025.herokuapp.com/centers/", HttpMethod.GET, null, String.class).getBody();
			Centers c = restTemplate.getForObject("https://fast-savannah-33025.herokuapp.com/centers", Centers.class);
			for (Center center : c.getComplain_centers()) {
				try {
					centerService.save(center);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
			return new ResponseEntity<>(c, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put(Constants.MESSAGE, Constants.QUERY_ERROR);
			response.put(Constants.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/center/{id}")
	public ResponseEntity<?> buscarCentro(@PathVariable String id){
		try {
			return new ResponseEntity<>(centerService.findById(id), HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put(Constants.MESSAGE, Constants.QUERY_ERROR);
			response.put(Constants.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
