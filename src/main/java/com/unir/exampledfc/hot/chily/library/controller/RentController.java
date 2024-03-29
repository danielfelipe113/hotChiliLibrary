package com.unir.exampledfc.hot.chily.library.controller;

import com.unir.exampledfc.hot.chily.library.dto.Rent.RentCreateDTO;
import com.unir.exampledfc.hot.chily.library.dto.Rent.RentDTO;
import com.unir.exampledfc.hot.chily.library.dto.Rent.RentUpdateDTO;
import com.unir.exampledfc.hot.chily.library.service.RentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Rent Controller", description = "Rent Controller Endpoints")
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

