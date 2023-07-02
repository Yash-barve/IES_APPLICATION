
package com.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.RolesEntity;

public interface RoleRepo extends JpaRepository<RolesEntity, Integer> {

}
