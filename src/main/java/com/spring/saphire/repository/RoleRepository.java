package com.spring.saphire.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.saphire.modal.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Set<Role> findByName(String name);
}
