package com.tuyano.web;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {

	List<UserEntity> findByNameContains(String name);
	
	List<UserEntity> findByEmailContains(String email);

	List<UserEntity> findByNameAndEmailContains(String name, String email);
}
