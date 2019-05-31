package com.helc.complain.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.helc.complain.entity.Complain;
import com.helc.complain.entity.InstitutionResponse;
import com.helc.complain.entity.Photo;
import com.helc.complain.exception.ComplainsNotFoundException;
import com.helc.complain.service.IComplainService;
import com.helc.complain.service.IPhotoService;
import com.helc.complain.service.IUploadFileService;
import com.helc.complain.util.Constants;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/api/v1/user")
public class ComplainRestController {

	@Autowired
	private IComplainService complainService;

	@Autowired
	private IUploadFileService fileService;

	@Autowired
	private IPhotoService photoService;

	private Map<String, Object> response = new HashMap<>();

	/**
	 * 
	 * @return
	 */
	@GetMapping("/complains/page/{page}")
	public ResponseEntity<?> index(@PathVariable Integer page) {
		try {
			Pageable pageable = PageRequest.of(page, Constants.NUMBER_PAGE);
			return new ResponseEntity<>(complainService.findAllComplains(pageable), HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put(Constants.MESSAGE, Constants.QUERY_ERROR);
			response.put(Constants.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (ComplainsNotFoundException e) {
			response.put(Constants.MESSAGE, "There are not registered complains");
			return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/complains/{userId}")
	public ResponseEntity<?> findComplainsByUser(@PathVariable String userId) {
		try {
			List<Complain> complains = complainService.findByUserId(userId);
			return new ResponseEntity<>(complains, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put(Constants.MESSAGE, Constants.QUERY_ERROR);
			response.put(Constants.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (ComplainsNotFoundException e) {
			response.put(Constants.MESSAGE, "There are not registered complains");
			return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/complains/{date}/page/{page}")
	public ResponseEntity<?> findByDate(@PathVariable Integer page, @PathVariable Date date) {
		try {
			Pageable pageable = PageRequest.of(page, Constants.NUMBER_PAGE);
			return new ResponseEntity<>(complainService.findAllComplains(pageable), HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put(Constants.MESSAGE, Constants.QUERY_ERROR);
			response.put(Constants.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (ComplainsNotFoundException e) {
			response.put(Constants.MESSAGE, "There are not registered complains");
			return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/complain/{id}")
	public ResponseEntity<?> show(@PathVariable String id) {
		Complain complain = null;
		try {
			complain = complainService.findById(id);
		} catch (DataAccessException e) {
			response.put(Constants.MESSAGE, Constants.QUERY_ERROR);
			response.put(Constants.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (complain == null) {
			response.put(Constants.MESSAGE,
					"The complain's id ".concat(id.concat("  doesn not exist in the database")));
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(complain, HttpStatus.OK);
	}

	/**
	 * 
	 * @param complain
	 * @param result
	 * @return
	 */
	/**@PostMapping("/complain")
	public ResponseEntity<?> create(@Valid @RequestBody Complain complain, @RequestParam String idUser,
			@RequestParam String idInstitution, BindingResult result) {
		Complain complainExample = null;
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "The field " + err.getField() + " " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			complainExample = complainService.save(complain, idUser, idInstitution);
			response.clear();
			response.put(Constants.MESSAGE, Constants.SUCCESSFUL_QUERY);
			response.put(Constants.ENTITY, complainExample);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put(Constants.MESSAGE, Constants.QUERY_ERROR);
			response.put(Constants.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (MessagingException e) {
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/
	@PostMapping("/complain")
	public ResponseEntity<?> create(@Valid @RequestBody Complain complain, BindingResult result){
		Complain complainExample = null;
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "The field " + err.getField() + " " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			complainExample = complainService.save(complain);
			response.clear();
			response.put(Constants.MESSAGE, Constants.SUCCESSFUL_QUERY);
			response.put(Constants.ENTITY, complainExample);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			response.put(Constants.MESSAGE, Constants.QUERY_ERROR);
			response.put(Constants.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (MessagingException e) {
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @param photoFile
	 * @param id
	 * @return
	 */
	@PostMapping("/complain/upload")
	public ResponseEntity<?> addEvidence(@RequestParam("photoFile") MultipartFile photoFile,
			@RequestParam("idComplain") String idComplain, @RequestParam("idUser") String idUser,
			@RequestParam("idInstitution") String idInstitution) {
		Complain complain = complainService.findById(idComplain);
		if (complain != null) {
			if (!photoFile.isEmpty()) {
				try {
					Photo photo = photoService.save(photoFile);
					//complain.setPhotoId(Constants.IMAGE_PATH + photo.getPhotoName());
					//complain.setPhotoName(photo.getPhotoName());
					complainService.save(complain, idUser, idInstitution);
					response.clear();
					response.put(Constants.MESSAGE, Constants.SUCCESSFUL_QUERY);
					return new ResponseEntity<>(response, HttpStatus.CREATED);
				} catch (IOException e) {
					response.clear();
					response.put(Constants.ERROR, "The file was not loaded.");
					return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				} catch (MessagingException e) {
					response.clear();
					response.put(Constants.MESSAGE, Constants.NOT_SEND_EMAIL);
					return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				response.clear();
				response.put(Constants.ERROR, "the file is empty");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} else {
			response.clear();
			response.put(Constants.MESSAGE, "Complain does not exist");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @param photoName
	 * @return
	 */
	@GetMapping("/complain/uploads/img/{photoName:.+}")
	public ResponseEntity<?> retriveEvidence(@PathVariable String photoName) {
		try {
			if (photoName.contains(Constants.DEFAULT_IMAGE)) {
				Resource resource = fileService.upload(photoName);
				HttpHeaders header = new HttpHeaders();
				header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
				header.setContentType(MediaType.IMAGE_JPEG);
				return new ResponseEntity<>(resource, header, HttpStatus.OK);
			} else {
				Photo photo = photoService.findByPhotoName(photoName);
				if (photo == null) {
					response.clear();
					response.put(Constants.ERROR, "The file was not founded.");
					return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
				} else {
					byte[] img = photo.getPhoto().getData();
					String imageBase64 = Base64.getEncoder().encodeToString(img);
					response.put("imageBase64", imageBase64);
					return new ResponseEntity<>(response, HttpStatus.OK);
				}
			}
		} catch (MalformedURLException e) {
			response.clear();
			response.put(Constants.ERROR, "The file was not loaded.");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @param response
	 * @param result
	 * @return
	 */
	@PostMapping("/complain/response")
	public ResponseEntity<?> createResponse(@Valid @RequestBody InstitutionResponse institutionResponse,
			@RequestParam("idComplain") String idComplain, BindingResult result) {
		Complain complain = complainService.findById(idComplain);
		if (complain != null) {
			complain = complainService.save(complain, institutionResponse);
			response.clear();
			response.put(Constants.MESSAGE, Constants.SUCCESSFUL_QUERY);
			response.put(Constants.ENTITY, complain);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			response.clear();
			response.put(Constants.MESSAGE, "Complain does not exist");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @param response
	 * @param result
	 * @return
	 */
	@GetMapping("/complain/report")
	public ResponseEntity<?> createReport(@RequestParam("sendDate") String sendDate) throws ComplainsNotFoundException {
		try {
			List<Complain> complains = complainService.findBySendDate(sendDate);
			return new ResponseEntity<>(complains, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put(Constants.MESSAGE, Constants.QUERY_ERROR);
			response.put(Constants.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/complain/coordinates")
	public ResponseEntity<?> getCoordinates() {
		try {
			return new ResponseEntity<>(complainService.findCoordinates(), HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put(Constants.MESSAGE, Constants.QUERY_ERROR);
			response.put(Constants.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
