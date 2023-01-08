package src.main.java.com.DL.services;

import src.main.java.com.BL.entities.Role;
import src.main.java.com.DL.constants.Messages;
import src.main.java.com.DL.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<String> deleteRole(Long roleId) {
     return null;
    }

    public ResponseEntity<String> updateRole(Role role, Long roleId) {
        return null;
    }

    public ResponseEntity<String> createNewRole(Role role) {
        roleRepository.save(role);
        return ResponseEntity.status(HttpStatus.OK).body(String.format(Messages.USER_CREATED_SUCCESS_MESSAGE, role.toString()));
    }

    public Optional<Role> getRoleById(Long roleId) {
        return roleRepository.findById(roleId);
    }

    public List<Role> getRoles() {
        return  roleRepository.findAll();
    }
}