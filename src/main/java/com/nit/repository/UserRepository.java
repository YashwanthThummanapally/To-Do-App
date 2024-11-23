package com.nit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.entity.UserEntity;
import com.nit.entity.UserLoginEntity;

public interface UserRepository extends JpaRepository<UserEntity, UserLoginEntity> {

}
