package com.example.demo.controllers;


import com.example.demo.entities.Unit;
import com.example.demo.entities.User;
import com.example.demo.services.UnitService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping("/api/v1/units")
public class UnitController {

    private final UnitService unitService;

    @Autowired
    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping
    public List<Unit> getAllUnits(){
        return this.unitService.getUnits();
    }

    @GetMapping("/{unitId}")
    public Optional<Unit> getUnitById(@PathVariable Long unitId){
        return this.unitService.getUnitById(unitId);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createNewUnit(@RequestBody Unit unit){
        System.out.println(unit);
        return this.unitService.createNewUnit(unit);
    }

    @PutMapping("/{unitId}")
    public ResponseEntity<String> updateUnit(@RequestBody Unit unit, @PathVariable Long unitId){
        return this.unitService.updateUnit(unit,unitId);
    }

    @DeleteMapping("/{unitId}")
    public ResponseEntity<String> deleteUnit(@PathVariable Long unitId){
        return this.unitService.deleteUnit(unitId);
    }
}
