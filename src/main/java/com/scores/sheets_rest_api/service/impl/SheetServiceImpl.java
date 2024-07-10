package com.scores.sheets_rest_api.service.impl;

import com.scores.sheets_rest_api.entity.SheetEntity;
import com.scores.sheets_rest_api.repository.SheetRepository;
import com.scores.sheets_rest_api.service.SheetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SheetServiceImpl implements SheetService {

    private final SheetRepository sheetRepository;

    public SheetServiceImpl(SheetRepository sheetRepository) {
        this.sheetRepository = sheetRepository;
    }

    @Override
    public List<SheetEntity> findAll() {
        return sheetRepository.findAll();
    }

    @Override
    public Optional<SheetEntity> findById(int id) {
        return sheetRepository.findById(id);
    }

    @Override
    public List<SheetEntity> findByTitle(String title) {
        return sheetRepository.findByTitle(title);
    }

    @Override
    public List<SheetEntity> findByDifficulty(int difficulty) {
        return sheetRepository.findByDifficulty(difficulty);
    }

    @Override
    public List<SheetEntity> findByAuthor(String author) {
        return sheetRepository.findByAuthor(author);
    }

    @Override
    public SheetEntity save(SheetEntity sheetEntity) {
        return sheetRepository.save(sheetEntity);
    }

    @Override
    public SheetEntity update(SheetEntity sheetEntity) {
        return sheetRepository.save(sheetEntity);
    }

    @Override
    public void deleteById(int id) {
        sheetRepository.deleteById(id);
    }
}
