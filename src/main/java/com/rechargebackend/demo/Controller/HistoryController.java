package com.rechargebackend.demo.Controller;

import com.rechargebackend.demo.Model.*;
import com.rechargebackend.demo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/History")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    // Create a new history record
    @PostMapping
    public ResponseEntity<History> createHistory(@RequestBody History history) {
        History createdHistory = historyService.createHistory(history);
        return ResponseEntity.status(201).body(createdHistory);
    }

    // Retrieve all history records
    @GetMapping
    public ResponseEntity<List<History>> getAllHistory() {
        return ResponseEntity.ok(historyService.getAllHistory());
    }

    // Retrieve a history record by ID
    @GetMapping("/{id}")
    public ResponseEntity<History> getHistoryById(@PathVariable Long id) {
        Optional<History> history = historyService.getHistoryById(id);
        return history.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update an existing history record
    @PutMapping("/{id}")
    public ResponseEntity<History> updateHistory(@PathVariable Long id, @RequestBody History updatedHistory) {
        Optional<History> existingHistory = historyService.getHistoryById(id);
        if (existingHistory.isPresent()) {
            History history = existingHistory.get();
            history.setUserId(updatedHistory.getUserId());
            history.setId(updatedHistory.getId());
            history.setDate(updatedHistory.getDate());
            history.setTime(updatedHistory.getTime());

            History updated = historyService.createHistory(history);
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a history record by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistory(@PathVariable Long id) {
        historyService.deleteHistory(id);
        return ResponseEntity.noContent().build();
    }
}
