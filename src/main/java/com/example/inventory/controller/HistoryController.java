package com.example.inventory.controller;

import com.example.inventory.model.History;
import com.example.inventory.repository.HistoryRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class HistoryController {

    @Autowired
    HistoryRepo repo;

    @GetMapping("/history")
    public List<History> getHistory(){
        return repo.findAll();
    }
}