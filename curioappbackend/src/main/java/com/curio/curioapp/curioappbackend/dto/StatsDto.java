package com.curio.curioapp.curioappbackend.dto;

public class StatsDto {
	private int itemsPosted;
	private int inquiriesMade;
	private int inquiriesReveived;
	private int itemsInWishlist;
	
	public int getItemsPosted() {
		return itemsPosted;
	}
	public void setItemsPosted(int itemsPosted) {
		this.itemsPosted = itemsPosted;
	}
	public int getInquiriesMade() {
		return inquiriesMade;
	}
	public void setInquiriesMade(int inquiriesMade) {
		this.inquiriesMade = inquiriesMade;
	}
	public int getInquiriesReveived() {
		return inquiriesReveived;
	}
	public void setInquiriesReveived(int inquiriesReveived) {
		this.inquiriesReveived = inquiriesReveived;
	}
	public int getItemsInWishlist() {
		return itemsInWishlist;
	}
	public void setItemsInWishlist(int itemsInWishlist) {
		this.itemsInWishlist = itemsInWishlist;
	}
	
}
