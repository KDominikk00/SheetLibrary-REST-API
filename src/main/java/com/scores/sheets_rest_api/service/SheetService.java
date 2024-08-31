    package com.scores.sheets_rest_api.service;

    import com.scores.sheets_rest_api.entity.SheetEntity;

    import java.util.List;
    import java.util.Optional;

    public interface SheetService {
        List<SheetEntity> findAll();
        Optional<SheetEntity> findById(int id);
        SheetEntity save(SheetEntity sheetEntity);
        SheetEntity update(SheetEntity sheetEntity);
        void deleteById(int id, String userId);
        List<SheetEntity> findAllByUserId(String userId);
    }