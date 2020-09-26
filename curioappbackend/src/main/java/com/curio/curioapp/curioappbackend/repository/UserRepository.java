package com.curio.curioapp.curioappbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curio.curioapp.curioappbackend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
