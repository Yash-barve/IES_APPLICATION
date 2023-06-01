package com.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.PlansEntity;

public interface PlansRepo extends JpaRepository<PlansEntity, Integer> {

}
