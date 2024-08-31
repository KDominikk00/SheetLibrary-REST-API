package com.scores.sheets_rest_api.repository;

import com.scores.sheets_rest_api.entity.SheetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SheetRepository extends JpaRepository<SheetEntity, Integer> {
    List<SheetEntity> findByUserId(String userId);
}
