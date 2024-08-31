package com.scores.sheets_rest_api;

import com.scores.sheets_rest_api.entity.SheetEntity;
import com.scores.sheets_rest_api.service.SheetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sheet")
public class SheetController {

    private final SheetService sheetService;

    public SheetController(SheetService sheetService) {
        this.sheetService = sheetService;
    }

    @GetMapping
    public List<SheetEntity> findAll(@RequestHeader(value = "userId", required = false) String userId) {
        if (userId != null) {
            return sheetService.findAllByUserId(userId);
        } else {
            return sheetService.findAll();
        }
    }

    // This method retrieves a sheet by its ID
    @GetMapping("/{id}")
    public ResponseEntity<SheetEntity> findById(@PathVariable("id") int id) {
        return sheetService.findById(id)
                .map(sheet -> ResponseEntity.ok(sheet))
                .orElse(ResponseEntity.notFound().build());
    }

    // This method saves a new sheet
    @PostMapping
    public ResponseEntity<SheetEntity> save(@RequestBody @Valid SheetEntity sheetEntity,
                                            @RequestHeader("userId") String userId) {
        sheetEntity.setUserId(userId); // Set the userId from the request header
        SheetEntity createdSheet = sheetService.save(sheetEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSheet);
    }

    // This method updates an existing sheet
    @PutMapping("/{id}")
    public ResponseEntity<SheetEntity> update(@PathVariable("id") int id,
                                              @RequestBody @Valid SheetEntity sheetEntity,
                                              @RequestHeader("userId") String userId) {
        sheetEntity.setId(id); // Ensure the ID in the request body matches the path variable
        sheetEntity.setUserId(userId); // Set the userId from the request header
        try {
            SheetEntity updatedSheet = sheetService.update(sheetEntity);
            return ResponseEntity.ok(updatedSheet);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    // This method deletes a sheet by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id,
                                       @RequestHeader("userId") String userId) {
        try {
            sheetService.deleteById(id, userId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}