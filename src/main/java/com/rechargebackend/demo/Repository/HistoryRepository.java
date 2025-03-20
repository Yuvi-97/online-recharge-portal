package com.rechargebackend.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rechargebackend.demo.Model.History;
import org.springframework.lang.NonNull;
import java.util.*;
import java.time.LocalDate;



@Repository
public interface HistoryRepository extends JpaRepository<History,Long>{
    @NonNull
    Optional<History> findById(@NonNull Long id);
    
    @Query("select h from History h where h.userId=:userid")
    List<History> getHistoryforUser(@Param("userid") Long userid);

    @Query("select h from History h where h.date between :start and :end")
    List<History> getHistoryforDate(@Param("start") LocalDate start, @Param("end") LocalDate end);
    
    List<History> findByDate(LocalDate date);
}
