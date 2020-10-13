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

import com.curio.curioapp.curioappbackend.dto.ItemDto;
import com.curio.curioapp.curioappbackend.service.ItemService;

@RestController
@RequestMapping("/api/items/")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@PostMapping
	public ResponseEntity postItem(@RequestBody ItemDto itemDto) {
		itemService.postItem(itemDto);
		return new ResponseEntity(HttpStatus.OK);
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<ItemDto>> showAllItems(){
		return new ResponseEntity<>(itemService.showAllItems(),HttpStatus.OK);
	}

}
