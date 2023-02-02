package activelearning.com.DL.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import activelearning.com.BL.entities.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
}