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
public class Gear {
	@Id
	@Column(name = "gearId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long gearId;
	@OneToOne
	private Item item;
	
	public long getGearId() {
		return gearId;
	}
	public void setGearId(long gearId) {
		this.gearId = gearId;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
}

