package com.curio.curioapp.curioappbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curio.curioapp.curioappbackend.model.Advertisement;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

}
