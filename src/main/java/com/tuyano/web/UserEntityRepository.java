package com.tuyano.web;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {

	List<UserEntity> findByName(String name);
	
	List<UserEntity> findByEmail(String email);

	List<UserEntity> findByNameContainingAndEmailContaining(String name, String email);
	
	UserEntity findByNameAndPassword(String name, String password);
}
