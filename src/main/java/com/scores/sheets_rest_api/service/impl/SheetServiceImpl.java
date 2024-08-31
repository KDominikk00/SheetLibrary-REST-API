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
    public List<SheetEntity> findAllByUserId(String userId) {
        return sheetRepository.findByUserId(userId);
    }

    @Override
    public Optional<SheetEntity> findById(int id) {
        return sheetRepository.findById(id);
    }

    @Override
    public SheetEntity save(SheetEntity sheetEntity) {
        return sheetRepository.save(sheetEntity);
    }

    @Override
    public SheetEntity update(SheetEntity sheetEntity) {
        Optional<SheetEntity> existingEntity = sheetRepository.findById(sheetEntity.getId());
        if (existingEntity.isPresent() && existingEntity.get().getUserId().equals(sheetEntity.getUserId())) {
            return sheetRepository.save(sheetEntity);
        } else {
            throw new IllegalArgumentException("Entity with ID " + sheetEntity.getId() + " does not exist or does not belong to the user.");
        }
    }

    @Override
    public void deleteById(int id, String userId) {
        Optional<SheetEntity> sheetEntity = sheetRepository.findById(id);
        if (sheetEntity.isPresent() && sheetEntity.get().getUserId().equals(userId)) {
            sheetRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Entity with ID " + id + " does not exist or does not belong to the user.");
        }
    }
}