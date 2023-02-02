package activelearning.com.API.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import activelearning.com.BL.entities.Role;
import activelearning.com.DL.services.RoleService;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<Role> getAllRoles(){
        return this.roleService.getRoles();
    }

    @GetMapping("/{roleId}")
    public Optional<Role> getRoleById(@PathVariable Long roleId){
        return this.roleService.getRoleById(roleId);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createNewRole(@RequestBody Role role){
        System.out.println(role);
        return this.roleService.createNewRole(role);
    }

    @PutMapping("/{unitId}")
    public ResponseEntity<String> updateRole(@RequestBody Role role, @PathVariable Long roleId){
        return this.roleService.updateRole(role,roleId);
    }

    @DeleteMapping("/{unitId}")
    public ResponseEntity<String> deleteRole(@PathVariable Long roleId){
        return this.roleService.deleteRole(roleId);
    }
}
