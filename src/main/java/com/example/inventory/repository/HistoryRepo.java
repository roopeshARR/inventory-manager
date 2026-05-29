package com.example.inventory.repository;

import com.example.inventory.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepo extends JpaRepository<History, Integer> {
}