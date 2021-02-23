package com.curio.curioapp.curioappbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curio.curioapp.curioappbackend.dto.AdvertisementDto;
import com.curio.curioapp.curioappbackend.service.AdvertisementService;

@RestController
@RequestMapping("/api/donationrequests/")
public class AdvertisementController {
	
	@Autowired
	private AdvertisementService advertisementService;
	
	@PostMapping
	public ResponseEntity<?> postAdvertisement(@RequestBody AdvertisementDto advertisementDto) {
		boolean posted=false;
		
		posted = advertisementService.postAdvertisement(advertisementDto);
		if(posted) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/alldonationrequests")
	public ResponseEntity<List<AdvertisementDto>> showAllAdvertisements() {
		return new ResponseEntity<>(advertisementService.showAllAdvertisements(),HttpStatus.OK);	
	}
}
