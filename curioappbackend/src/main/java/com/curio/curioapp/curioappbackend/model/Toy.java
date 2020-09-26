package com.curio.curioapp.curioappbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Toy {
	@Id
	@Column(name = "toyId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long toyId;
	@OneToOne
	private Item item;
	
	public long getToyId() {
		return toyId;
	}
	public void setToyId(long toyId) {
		this.toyId = toyId;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
	
}