package com.rechargebackend.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rechargebackend.demo.Model.History;

@Repository
public interface HistoryRepository extends JpaRepository<History,Long>{
    
}
