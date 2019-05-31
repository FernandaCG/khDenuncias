package com.helc.complain.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helc.complain.exception.ComplainsNotFoundException;
import com.helc.complain.exception.DuctosNotFoundException;
import com.helc.complain.service.IDuctosService;
import com.helc.complain.util.Constants;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/api/v1/user")
public class DuctosRestController {

	@Autowired
	private IDuctosService ductosService;
	
	private Map<String, Object> response = new HashMap<>();
	
	@GetMapping("/ductos/page/{page}")
	public ResponseEntity<?> index(@PathVariable Integer page) {
		try {
			Pageable pageable = PageRequest.of(page, Constants.NUMBER_PAGE);
			return new ResponseEntity<>(ductosService.findAllDuctos(pageable), HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put(Constants.MESSAGE, Constants.QUERY_ERROR);
			response.put(Constants.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (DuctosNotFoundException e) {
			response.put(Constants.MESSAGE, "There are not registered complains");
			return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
		}
	}
}
