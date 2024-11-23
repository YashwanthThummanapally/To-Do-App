package com.nit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.entity.ToDoTaskEntity;
import com.nit.entity.UserEntity;

public interface ToDoTaskRepository extends JpaRepository<ToDoTaskEntity, String> {

	// SELECT * FROM TODOTASK_ASHOK WHERE USER=:USER_ENTITY
	public List<ToDoTaskEntity> findByUser(UserEntity userEntity);
}
