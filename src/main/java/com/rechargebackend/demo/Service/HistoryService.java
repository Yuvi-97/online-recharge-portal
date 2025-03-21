package com.rechargebackend.demo.Service;

import com.rechargebackend.demo.Model.*;
import com.rechargebackend.demo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;

    public History createHistory(History history) {
        return historyRepository.save(history);
    }

    public List<History> getAllHistory() {
        return historyRepository.findAll();
    }

    public Optional<History> getHistoryById(Long id) {
        return historyRepository.findById(id);
    }

    public void deleteHistory(Long id) {
        historyRepository.deleteById(id);
    }

    public List<History> gethistorybydate(LocalDate date){
        return historyRepository.findByDate(date);
    }

    public List<History> getHistorybyrange(LocalDate start,LocalDate end){
        return historyRepository.getHistoryforDate(start, end);
    }
}
