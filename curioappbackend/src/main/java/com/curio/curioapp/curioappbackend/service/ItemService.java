package com.curio.curioapp.curioappbackend.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.curio.curioapp.curioappbackend.dto.AddToInquiredItemsRequest;
import com.curio.curioapp.curioappbackend.dto.ItemDto;
import com.curio.curioapp.curioappbackend.model.Item;
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
	
	
	public void postItem(ItemDto itemDto) {
		Item item = new Item();
		User username = authservice.getCurrentUser().orElseThrow(()->
				new IllegalArgumentException("No user logged in"));
		
		
		if (username!=null) {
			item.setItemId(itemDto.getItemId());
			item.setItemName(itemDto.getItemName());
			item.setType(itemDto.getType());
			item.setPostedTimeStamp(Instant.now());
			item.setDescription(itemDto.getDescription());
			Optional<com.curio.curioapp.curioappbackend.model.User> optionalUser = userRepository.findByUsername(username.getUsername());
			com.curio.curioapp.curioappbackend.model.User user= optionalUser.get(); 
			item.setPostedUser(user);
			
			itemRepository.save(item);
			
		}
				
		
	}
	
	public List<ItemDto> showAllItems(){
		List<Item> items = itemRepository.findAll();
		return items.stream().map(this::mapFromItemToDto).collect(Collectors.toList());
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
					addInquiredItem.setPostedTimeStamp(Instant.now());
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
	
	private ItemDto mapFromItemToDto(Item item) {
		ItemDto itemDto = new ItemDto();
		itemDto.setItemId(item.getItemId());
		itemDto.setItemName(item.getItemName());
		itemDto.setType(item.getType());
		itemDto.setDescription(item.getDescription());
		return itemDto;
	}

}
