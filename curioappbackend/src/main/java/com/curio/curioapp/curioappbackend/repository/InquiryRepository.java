package com.curio.curioapp.curioappbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curio.curioapp.curioappbackend.model.Inquiry;
import com.curio.curioapp.curioappbackend.model.Item;


public interface InquiryRepository extends JpaRepository <Inquiry, Long>{
	List<Inquiry> findByInquiredItem(Item item);

}
