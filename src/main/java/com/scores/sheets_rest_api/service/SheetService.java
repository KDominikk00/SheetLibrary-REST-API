package com.scores.sheets_rest_api.service;

import com.scores.sheets_rest_api.entity.SheetEntity;

import java.util.List;
import java.util.Optional;

public interface SheetService {
    List<SheetEntity> findAll();
    Optional<SheetEntity> findById(int id);
    List<SheetEntity> findByTitle(String title);
    List<SheetEntity> findByDifficulty(int difficulty);
    List<SheetEntity> findByAuthor(String author);
    SheetEntity save(SheetEntity sheetEntity);
    SheetEntity update(SheetEntity sheetEntity);
    void deleteById(int id);
}
