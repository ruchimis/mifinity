package com.mifinity.repository;

import com.mifinity.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(String role);
}