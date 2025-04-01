package com.rechargebackend.demo.Controller;

import com.rechargebackend.demo.Model.*;
import com.rechargebackend.demo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/History")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @Autowired
    private UserService userService;

    // Create a new history record for a user
    @PostMapping("/{userId}")
    public ResponseEntity<History> createHistory(@PathVariable Long userId, @RequestBody History history) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            history.setUser(user.get());
            History createdHistory = historyService.createHistory(history);
            return ResponseEntity.status(201).body(createdHistory);
        }
        return ResponseEntity.notFound().build();
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

    // Delete a history record
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistory(@PathVariable Long id) {
        historyService.deleteHistory(id);
        return ResponseEntity.noContent().build();
    }

    // Retrieve history by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<History>> getHistoryByUser(@PathVariable Long userId) {
        List<History> histories = historyService.getHistoryByUser(userId);
        return ResponseEntity.ok(histories);
    }

    // Retrieve history by date
    @GetMapping("/findByDate")
    public List<History> getHistoryByDate(@RequestParam("date") String date) {
        LocalDate localDate = LocalDate.parse(date);
        return historyService.getHistoryByDate(localDate);
    }

    // Retrieve history within a date range
    @GetMapping("/getbyrange")
    public List<History> getByRange(@RequestParam("start") LocalDate start, @RequestParam("end") LocalDate end) {
        return historyService.getHistoryByRange(start, end);
    }
}
