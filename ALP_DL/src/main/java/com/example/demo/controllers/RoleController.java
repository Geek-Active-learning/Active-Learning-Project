package com.example.demo.controllers;

import com.example.demo.entities.Role;
import com.example.demo.entities.Unit;
import com.example.demo.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
