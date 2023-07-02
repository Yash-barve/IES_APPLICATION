package com.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.RolesManagerEntity;

public interface RoleMangerRepo extends JpaRepository<RolesManagerEntity, Integer> {

}
