package com.curio.curioapp.curioappbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curio.curioapp.curioappbackend.model.Item;
import com.curio.curioapp.curioappbackend.model.User;

public interface ItemRepository extends JpaRepository<Item, Long> {

	List<Item> findByPostedUser(User user);
	List<Optional<Item>> findByType(String type);
}
