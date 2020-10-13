package com.curio.curioapp.curioappbackend.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

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
	
	private ItemDto mapFromItemToDto(Item item) {
		ItemDto itemDto = new ItemDto();
		itemDto.setItemId(item.getItemId());
		itemDto.setItemName(item.getItemName());
		itemDto.setType(item.getType());
		itemDto.setDescription(item.getDescription());
		return itemDto;
	}

}
