package com.springboot.kickstart.repository;

import com.springboot.kickstart.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
