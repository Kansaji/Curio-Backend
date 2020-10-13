package com.curio.curioapp.curioappbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curio.curioapp.curioappbackend.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
