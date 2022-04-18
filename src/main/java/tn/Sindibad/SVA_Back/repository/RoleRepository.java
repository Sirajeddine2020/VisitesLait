package tn.Sindibad.SVA_Back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.Sindibad.SVA_Back.model.ERole;
import tn.Sindibad.SVA_Back.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
        Optional<Role> findByName(ERole name);

        }
