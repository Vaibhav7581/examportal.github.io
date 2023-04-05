package com.examportal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
