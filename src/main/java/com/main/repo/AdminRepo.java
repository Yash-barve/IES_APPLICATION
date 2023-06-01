package com.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.AdminEntity;

@Repository
public interface AdminRepo extends JpaRepository<AdminEntity, Integer> {

    public AdminEntity findByEmail(String email);
    
    public AdminEntity findByEmailAndPassword(String email, String password);
	
}
