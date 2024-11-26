package com.example.products.repositories;

import com.example.products.enums.RoleName;
import com.example.products.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findByRoleName(RoleName roleName);

}
