package com.unir.exampledfc.HotChiliLibrary.controller;

import com.unir.exampledfc.HotChiliLibrary.dto.Rent.RentCreateDTO;
import com.unir.exampledfc.HotChiliLibrary.dto.Rent.RentDTO;
import com.unir.exampledfc.HotChiliLibrary.dto.Rent.RentUpdateDTO;
import com.unir.exampledfc.HotChiliLibrary.service.RentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rent")
public class RentController {
    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping
    public ResponseEntity<List<RentDTO>> getAllRents() {
        return ResponseEntity.ok(rentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentDTO> getRentById(@PathVariable Long id) {
        return rentService.findById(id);
    }

    @PostMapping
    public ResponseEntity<RentDTO> createRent(@RequestBody RentCreateDTO rentCreateDTO) {
        RentDTO savedRent = rentService.save(rentCreateDTO);
        return ResponseEntity.ok(savedRent);
    }

    @PutMapping("/{id}")
    public RentDTO updateRent(@PathVariable Long id, @RequestBody RentUpdateDTO rentUpdateDTO) {
        return rentService.update(id, rentUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRent(@PathVariable Long id) {
        rentService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

