package com.curio.curioapp.curioappbackend.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curio.curioapp.curioappbackend.dto.AdvertisementDto;
import com.curio.curioapp.curioappbackend.model.Advertisement;
import com.curio.curioapp.curioappbackend.repository.AdvertisementRepository;

@Service
public class AdvertisementService {

	@Autowired
	private AdvertisementRepository advertisementRepository;
	
	public boolean postAdvertisement(AdvertisementDto advertisementDto) {
		boolean posted=false;
		
		Advertisement advertisement = new Advertisement();
		advertisement.setContactDetails(advertisementDto.getContactDetails());
		advertisement.setDescription(advertisementDto.getDescription());
		advertisement.setOrganization(advertisementDto.getOrganization());
		advertisement.setSubject(advertisementDto.getSubject());
		advertisement.setExpiryDate(advertisementDto.getExpiryDate());
		advertisement.setPostedDate(advertisementDto.getPostedDate());
		Advertisement saved = advertisementRepository.save(advertisement);
	
		if(saved!=null) {
			posted=true;
		}
		
		return posted;
	}
	
	
	public List<AdvertisementDto> showAllAdvertisements(){
		
		List<Advertisement> advertisements = new ArrayList<>();
		advertisements=advertisementRepository.findAll();
		List<Advertisement> sendingAdvertisements = new ArrayList<>();
		
		for(Advertisement a:advertisements) {
			if(isExpired(a.getExpiryDate())) {
				advertisementRepository.deleteById(a.getAdvertisementId());;
			}
			else {
				sendingAdvertisements.add(a);
			}
		}
		
		return sendingAdvertisements.stream().map(this::mapFromAdvertisementToDto).collect(Collectors.toList());
	}
	
	
	private AdvertisementDto mapFromAdvertisementToDto(Advertisement advertisement){
		AdvertisementDto advertisementDto = new AdvertisementDto();
		advertisementDto.setAdvertisementId(advertisement.getAdvertisementId());
		advertisementDto.setContactDetails(advertisement.getContactDetails());
		advertisementDto.setDescription(advertisement.getDescription());
		advertisementDto.setOrganization(advertisement.getOrganization());
		advertisementDto.setSubject(advertisement.getSubject());
		advertisementDto.setPostedDate(advertisement.getPostedDate());
		advertisementDto.setExpiryDate(advertisement.getExpiryDate());
		
		return advertisementDto;
	}
	
	private boolean isExpired(String date) {
		boolean expired=false;
		Date expiryDate;
		
		if(date==null) {
			return true;
		}
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			expiryDate=df.parse(date);
			Date todayDate= new Date();
			String todayStr = df.format(todayDate);
			Date today = df.parse(todayStr);
			System.out.println(today+"   "+expiryDate);
			if(today.after(expiryDate)){
				expired=true;
			}
			
		}catch(Throwable e) {
			System.out.println("Exception occured");
		}
		
		
		
		
		
		return expired;
		
	}
}
