package com.curio.curioapp.curioappbackend.service;


import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.curio.curioapp.curioappbackend.dto.AddToInquiredItemsRequest;
import com.curio.curioapp.curioappbackend.dto.CoordinatesDto;
import com.curio.curioapp.curioappbackend.dto.InquiryRequest;
import com.curio.curioapp.curioappbackend.dto.InquiryResponse;
import com.curio.curioapp.curioappbackend.dto.ItemDto;
import com.curio.curioapp.curioappbackend.model.Inquiry;
import com.curio.curioapp.curioappbackend.model.Item;
import com.curio.curioapp.curioappbackend.repository.InquiryRepository;
import com.curio.curioapp.curioappbackend.repository.ItemRepository;
import com.curio.curioapp.curioappbackend.repository.UserRepository;
@Service
public class ItemService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthService authservice;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private InquiryRepository inquiryRepository;
	
	
	public void postItem(ItemDto itemDto) {
		Item item = new Item();
		User username = authservice.getCurrentUser().orElseThrow(()->
				new IllegalArgumentException("No user logged in"));
		
		
		if (username!=null) {
			item.setItemId(itemDto.getItemId());
			item.setItemName(itemDto.getItemName());
			item.setType(itemDto.getType());
			item.setPostedTimeStamp(itemDto.getPostedTimeStamp());
			item.setDescription(itemDto.getDescription());
			byte[] decodedByte = Base64.getEncoder().encode(itemDto.getPhoto().getBytes());
			item.setPhoto(decodedByte);
			Optional<com.curio.curioapp.curioappbackend.model.User> optionalUser = userRepository.findByUsername(username.getUsername());
			com.curio.curioapp.curioappbackend.model.User user= optionalUser.get(); 
			item.setPostedUser(user);
			
			itemRepository.save(item);
			
		}
				
		
	}
	
	public List<ItemDto> showAllItems(int distanceValue){
		List<Item> items = itemRepository.findAll();
		List<Item> sendingItemList = new ArrayList<>();
		System.out.println(distanceValue);
		double d=0;

		User username = authservice.getCurrentUser().orElseThrow(()->
		new IllegalArgumentException("No user logged in"));

		if(username!=null) {
			Optional<com.curio.curioapp.curioappbackend.model.User> optionalUser = userRepository.findByUsername(username.getUsername());
			com.curio.curioapp.curioappbackend.model.User user= optionalUser.get();
			if(distanceValue>=100) {
				for(Item i:items) {
					if(!user.getInquiredItems().contains(i) && !i.getPostedUser().equals(user)) {
						sendingItemList.add(i);
					}
				}
			}else if(distanceValue<100) {
				for(Item i:items) {
					double longitudeC=user.getLongitude();
					double latitudeC=user.getLatitude();
					double longitudeP=i.getPostedUser().getLongitude();
					double latitudeP=i.getPostedUser().getLatitude();
					
					 d=distance(longitudeC,latitudeC,longitudeP,latitudeP);
					System.out.println(d);
				
					if(!user.getInquiredItems().contains(i) && !i.getPostedUser().equals(user) &&  d <= distanceValue) {
						sendingItemList.add(i);
					}
				}
		
			}
		}
		return sendingItemList.stream().map(this::mapFromItemToDto).collect(Collectors.toList());
	}
	
	public List<ItemDto> showMyItems(){
		User username = authservice.getCurrentUser().orElseThrow(()->
				new IllegalArgumentException("No user logged in"));
		List<Item> myItems = null;
		if(username!=null) {
			Optional<com.curio.curioapp.curioappbackend.model.User> optionalUser = userRepository.findByUsername(username.getUsername());
			com.curio.curioapp.curioappbackend.model.User user= optionalUser.get();
		    myItems = itemRepository.findByPostedUser(user);	
		}		
		return myItems.stream().map(this::mapFromItemToDto).collect(Collectors.toList());
	}
	
	public boolean addToInquiredItems(AddToInquiredItemsRequest addToInquiredItemsRequest) {
		
		boolean added = false;
		User username = authservice.getCurrentUser().orElseThrow(()->
				new IllegalArgumentException("No user logged in"));
		
		if(username!=null) {
			Optional<com.curio.curioapp.curioappbackend.model.User> optionalUser = userRepository.findByUsername(username.getUsername());
			com.curio.curioapp.curioappbackend.model.User user= optionalUser.get();
			
			Optional<Item> optionalItem = itemRepository.findById(addToInquiredItemsRequest.getItemId());
			
			if(optionalItem!=null) {
				Item item = optionalItem.get();
				if (item.getPostedUser()!= user) {
					Item addInquiredItem = new Item();
					addInquiredItem.setItemId(addToInquiredItemsRequest.getItemId());
					addInquiredItem.setItemName(item.getItemName());
					addInquiredItem.setType(item.getType());
					addInquiredItem.setPostedTimeStamp(item.getPostedTimeStamp());
					addInquiredItem.setDescription(item.getDescription());
					user.getInquiredItems().add(item);
					userRepository.save(user);
					added = true;
				}
			}	
		}
		return added;	
	}
	
	public List<ItemDto> showMyWishlist(){
		
		User username = authservice.getCurrentUser().orElseThrow(()->
				new IllegalArgumentException("No user logged in"));
		List<Item> myWishlist = null;
		if(username!=null) {
			Optional<com.curio.curioapp.curioappbackend.model.User> optionalUser = userRepository.findByUsername(username.getUsername());
			com.curio.curioapp.curioappbackend.model.User user= optionalUser.get();
			myWishlist = user.getInquiredItems();
		}		
		return myWishlist.stream().map(this::mapFromItemToDto).collect(Collectors.toList());
		
	}
	
	public List<InquiryResponse> getInquiries(long itemId){

		List<Inquiry> sendingInquiries = new ArrayList<>();
		User username = authservice.getCurrentUser().orElseThrow(()->
		new IllegalArgumentException("No user logged in"));
		if(username!=null) {
			Optional<com.curio.curioapp.curioappbackend.model.User> optionalUser = userRepository.findByUsername(username.getUsername());
			com.curio.curioapp.curioappbackend.model.User user= optionalUser.get();
			
			
			Optional<Item> itemFound = itemRepository.findById(itemId);
			Item inquiredItem = itemFound.get();
			if(inquiredItem!= null) {
				List<Inquiry> inquiries = inquiryRepository.findByInquiredItem(inquiredItem);
				for(Inquiry i: inquiries) {
					if(i.getSentBy().equals(user) || i.getReceivedBy().equals(user)) {
						sendingInquiries.add(i);
					}
				}
			}
		}
		
		
		return sendingInquiries.stream().map(this::mapFromInquiryToDto).collect(Collectors.toList());
		
	}
	
	
	public boolean makeInquiry(InquiryRequest inquiryRequest) {
	
		boolean made=false;
		User username = authservice.getCurrentUser().orElseThrow(()->
		new IllegalArgumentException("No user logged in"));
		if(username!=null) {
			Optional<com.curio.curioapp.curioappbackend.model.User> optionalUser = userRepository.findByUsername(username.getUsername());
			com.curio.curioapp.curioappbackend.model.User user= optionalUser.get();
			
			Optional<Item> itemFound = itemRepository.findById(inquiryRequest.getItemId());
			Item inquiredItem = itemFound.get();
			if(inquiredItem!=null) {
				Inquiry inquiry=new Inquiry();
				inquiry.setSentBy(user);
				inquiry.setReceivedBy(inquiredItem.getPostedUser());
				inquiry.setInquiredItem(inquiredItem);
				inquiry.setMessageContent(inquiryRequest.getMessage());
				inquiry.setInquiredTimeStamp(inquiryRequest.getTimeStamp());
				inquiryRepository.save(inquiry);
				made=true;
			}
			
			
		}
		return made;
	}
	
	public boolean reply(InquiryRequest inquiryRequest) {
		boolean made=false;
		
		User username = authservice.getCurrentUser().orElseThrow(()->
		new IllegalArgumentException("No user logged in"));
		if(username!=null) {
			Optional<com.curio.curioapp.curioappbackend.model.User> optionalUser = userRepository.findByUsername(username.getUsername());
			com.curio.curioapp.curioappbackend.model.User user= optionalUser.get();
			
			Optional<Item> itemFound = itemRepository.findById(inquiryRequest.getItemId());
			Item inquiredItem = itemFound.get();
			
			Optional<com.curio.curioapp.curioappbackend.model.User> optionalToUser = userRepository.findByUsername(inquiryRequest.getTo());
			com.curio.curioapp.curioappbackend.model.User toUser= optionalToUser.get();
			if(inquiredItem!=null) {
				Inquiry inquiry = new Inquiry();
				inquiry.setInquiredItem(inquiredItem);
				inquiry.setSentBy(user);
				inquiry.setReceivedBy(toUser);
				inquiry.setMessageContent(inquiryRequest.getMessage());
				inquiry.setInquiredTimeStamp(inquiryRequest.getTimeStamp());
				
				inquiryRepository.save(inquiry);
				made=true;
				
				
			}
			
		}
		return made;
	}
	
	 public Boolean removeFromWishlist(long itemId) {
		 boolean removed=false;
		 com.curio.curioapp.curioappbackend.model.User user= getCurrentlyLoggedInUser();
		 Optional<Item> itemFound = itemRepository.findById(itemId);
		 Item item = itemFound.get();
		 if(item!=null) {
			 if(user.getInquiredItems().contains(item)) {
				 List<Item> inquiredItems=user.getInquiredItems();
				 removed = user.getInquiredItems().remove(item);
				 user.setInquiredItems(inquiredItems);
				 userRepository.save(user);
				 
			 }
		 }
		 
		 return removed;
	 }
	 
	 public boolean updateCurrentGeolocation(CoordinatesDto coordinatesDto) {
		 com.curio.curioapp.curioappbackend.model.User user=getCurrentlyLoggedInUser();
		 boolean updated=false;
		 if(user!=null) {
			 user.setLongitude(coordinatesDto.getLongitude());
			 user.setLatitude(coordinatesDto.getLatitude());
			 userRepository.save(user);
			 updated=true;			 
		 }
		 return updated;
	 }
	 
	
	private ItemDto mapFromItemToDto(Item item) {
		ItemDto itemDto = new ItemDto();
		itemDto.setItemId(item.getItemId());
		itemDto.setItemName(item.getItemName());
		itemDto.setType(item.getType());
		itemDto.setDescription(item.getDescription());
		itemDto.setPostedTimeStamp(item.getPostedTimeStamp());
		itemDto.setSoldFlag(item.getSoldFlag());
		byte[] decoded = Base64.getDecoder().decode(item.getPhoto());
		itemDto.setPhoto(new String(decoded));
		itemDto.setPostedUser(item.getPostedUser().getUsername());
		
		com.curio.curioapp.curioappbackend.model.User user=getCurrentlyLoggedInUser();
		double longitudeC=user.getLongitude();
		double latitudeC=user.getLatitude();
		double longitudeP=item.getPostedUser().getLongitude();
		double latitudeP=item.getPostedUser().getLatitude();
		
		double d=distance(longitudeC,latitudeC,longitudeP,latitudeP);
		itemDto.setAway(d);
		return itemDto;
	}
	
	private InquiryResponse mapFromInquiryToDto(Inquiry inquiry) {
		InquiryResponse inquiryResponse = new InquiryResponse();
		inquiryResponse.setFrom(inquiry.getSentBy().getUsername());
		inquiryResponse.setTo(inquiry.getReceivedBy().getUsername());
		inquiryResponse.setItemId(inquiry.getInquiredItem().getItemId());
		inquiryResponse.setMessage(inquiry.getMessageContent());
		inquiryResponse.setInquireTimeStamp(inquiry.getInquiredTimeStamp());
		return inquiryResponse;
	}

	private com.curio.curioapp.curioappbackend.model.User getCurrentlyLoggedInUser() {
		com.curio.curioapp.curioappbackend.model.User user=null;
		User username = authservice.getCurrentUser().orElseThrow(()->
		new IllegalArgumentException("No user logged in"));
		if(username!=null) {
			Optional<com.curio.curioapp.curioappbackend.model.User> optionalUser = userRepository.findByUsername(username.getUsername());
			user= optionalUser.get();
		}
		return user;
	}
	
	private double distance(double longitudeC, double latitudeC, double longitudeP, double latitudeP) {
		longitudeC=Math.toRadians(longitudeC);
		latitudeC=Math.toRadians(latitudeC);
		longitudeP=Math.toRadians(longitudeP);
		latitudeP=Math.toRadians(latitudeP);
		
		double dLongitude=longitudeP-longitudeC;
		double dLatitude=latitudeP-latitudeC;
		
		double a=Math.pow(Math.sin(dLatitude/2),2)+Math.cos(latitudeC)*Math.cos(latitudeP)*Math.pow(Math.sin(dLongitude/2),2 );
		double c=2*Math.asin(Math.sqrt(a));
		double r=6371;
		return (c*r);
		
		
	}
}
