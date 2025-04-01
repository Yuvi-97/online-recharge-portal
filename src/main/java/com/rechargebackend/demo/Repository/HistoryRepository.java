package com.rechargebackend.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rechargebackend.demo.Model.History;
import com.rechargebackend.demo.Model.User;
import org.springframework.lang.NonNull;
import java.util.*;
import java.time.LocalDate;

@Repository
public interface HistoryRepository extends JpaRepository<History,Long>{
    
    @NonNull
    Optional<History> findById(@NonNull Long id);

    // Updated to use User instead of userId
    @Query("SELECT h FROM History h WHERE h.user = :user")
    List<History> getHistoryForUser(@Param("user") User user);

    @Query("SELECT h FROM History h WHERE h.date BETWEEN :start AND :end")
    List<History> getHistoryForDate(@Param("start") LocalDate start, @Param("end") LocalDate end);
    
    List<History> findByDate(LocalDate date);
}
