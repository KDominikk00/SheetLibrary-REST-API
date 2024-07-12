package com.scores.sheets_rest_api;

import com.scores.sheets_rest_api.entity.SheetEntity;
import com.scores.sheets_rest_api.service.SheetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sheet")
public class SheetController {

    private final SheetService sheetService;

    public SheetController(SheetService employeeService) {
        this.sheetService = employeeService;
    }

    @GetMapping
    public List<SheetEntity> findAll() {
        return sheetService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SheetEntity> findById(@PathVariable("id") int id) {
        return sheetService.findById(id)
                .map(sheet -> ResponseEntity.ok(sheet))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/title/{title}")
    public List<SheetEntity> findByTitle(@PathVariable("title") String title) {
        return sheetService.findByTitle(title);
    }

    @GetMapping("/difficulty/{difficulty}")
    public List<SheetEntity> findByDifficulty(@PathVariable("difficulty") int difficulty) {
        return sheetService.findByDifficulty(difficulty);
    }

    @GetMapping("/author/{author}")
    public List<SheetEntity> findByAuthor(@PathVariable("author") String author) {
        return sheetService.findByAuthor(author);
    }

    @PostMapping
    public ResponseEntity<SheetEntity> save(@RequestBody @Valid SheetEntity sheetEntity) {
        SheetEntity createdSheet = sheetService.save(sheetEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSheet);
    }

    @PutMapping
    public SheetEntity update(@RequestBody SheetEntity sheetEntity) {
        return sheetService.update(sheetEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        sheetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
