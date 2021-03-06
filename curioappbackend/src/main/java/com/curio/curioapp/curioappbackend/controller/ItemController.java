package com.curio.curioapp.curioappbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curio.curioapp.curioappbackend.dto.AddToInquiredItemsRequest;
import com.curio.curioapp.curioappbackend.dto.CoordinatesDto;
import com.curio.curioapp.curioappbackend.dto.InquiryRequest;
import com.curio.curioapp.curioappbackend.dto.InquiryResponse;
import com.curio.curioapp.curioappbackend.dto.ItemDto;
import com.curio.curioapp.curioappbackend.dto.StatsDto;
import com.curio.curioapp.curioappbackend.service.ItemService;

@RestController
@RequestMapping("/api/items/")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@PostMapping
	public ResponseEntity<?> postItem(@RequestBody ItemDto itemDto) {
		itemService.postItem(itemDto);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@GetMapping("/all/{distanceValue}")
	public ResponseEntity<List<ItemDto>> showAllItems(@PathVariable int distanceValue){
		return new ResponseEntity<>(itemService.showAllItems(distanceValue),HttpStatus.OK);
	}
	
	@GetMapping("/item/{itemId}")
	public ResponseEntity<List<ItemDto>> getItemInfo(@PathVariable long itemId){
		return new ResponseEntity<>(itemService.getItemInfo(itemId),HttpStatus.OK);
	}
	
	@GetMapping("/myitems")
	public ResponseEntity<List<ItemDto>> showMyItems(){
		return new ResponseEntity<>(itemService.showMyItems(),HttpStatus.OK);
	}
	
	@PostMapping("/addtoinquireditems")
	public ResponseEntity<?> addToInquiredItems(@RequestBody AddToInquiredItemsRequest addToInquiredItemsRequest) {
		
		boolean added=itemService.addToInquiredItems(addToInquiredItemsRequest);
		if(added) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/mywishlist")
	public ResponseEntity<List<ItemDto>> showMyWishlist(){
		return new ResponseEntity<>(itemService.showMyWishlist(),HttpStatus.OK);
	}
	
	@GetMapping("/getinquiries/{itemId}")
	public ResponseEntity<List<InquiryResponse>> getInquiries(@PathVariable long itemId){
		return new ResponseEntity<>(itemService.getInquiries(itemId),HttpStatus.OK);
	}
	
	@PostMapping("/makeinquiry")
	public ResponseEntity<?> makeInquiry(@RequestBody InquiryRequest inquiryRequest) {
		
		boolean made=itemService.makeInquiry(inquiryRequest);
		if(made) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	@PostMapping("/reply")
	public ResponseEntity<?> reply(@RequestBody InquiryRequest inquiryRequest) {
		
		boolean made=itemService.reply(inquiryRequest);
		if(made) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@DeleteMapping("/removeinquiry/{inquiryId}")
	public ResponseEntity<?> removeInquiry(@PathVariable long inquiryId){
		boolean removed=itemService.removeInquiry(inquiryId);
		if(removed) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@PostMapping("/removefromwishlist/{itemId}")
	public ResponseEntity<?> removeFromWishlist(@PathVariable long itemId){
		boolean removed=itemService.removeFromWishlist(itemId);
		if(removed) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/removefrommyitems/{itemId}")
	public ResponseEntity<?> removeFromMyItems(@PathVariable long itemId){
		boolean removed=itemService.removeFromMyItems(itemId);
		if(removed) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}

	@PutMapping("/updatecurrentgeolocation")
	public ResponseEntity<?> updateCurrentGeolocation(@RequestBody CoordinatesDto coordinatesDto){
		boolean updated=itemService.updateCurrentGeolocation(coordinatesDto);
		if(updated) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}
	
	@GetMapping("/getmystats")
	public ResponseEntity<StatsDto> getMyStats(){
		return new ResponseEntity<>(itemService.getMyStats(),HttpStatus.OK);
	}
	
	@GetMapping("/getitemsbyusername/{distanceValue}/{username}")
	public ResponseEntity<List<ItemDto>> getItemsByUsername(@PathVariable int distanceValue, @PathVariable String username ) {
		return new ResponseEntity<>(itemService.getItemsByUsername(distanceValue, username),HttpStatus.OK);
	}
	
	@GetMapping("/getitemsbyitemtype/{distanceValue}/{type}")
	public ResponseEntity<List<ItemDto>> getItemsByitemtype(@PathVariable int distanceValue, @PathVariable String type ) {
		return new ResponseEntity<>(itemService.getItemsByType(distanceValue, type),HttpStatus.OK);
	}
	
	@GetMapping("/getitemsbyitemname/{distanceValue}/{itemName}")
	public ResponseEntity<List<ItemDto>> getItemsByitemname(@PathVariable int distanceValue, @PathVariable String itemName ) {
		return new ResponseEntity<>(itemService.getItemsByItemName(distanceValue, itemName),HttpStatus.OK);
	}
	
	@PutMapping("/setsoldflag/{itemId}/{flag}")
	public ResponseEntity<?> setSoldFlag(@PathVariable long itemId, @PathVariable String flag){
		boolean set=itemService.setSoldFlag(itemId, flag);
		if(set) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
		
	}
}
