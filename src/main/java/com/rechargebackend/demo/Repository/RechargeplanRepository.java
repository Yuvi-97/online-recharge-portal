package com.rechargebackend.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.rechargebackend.demo.Model.RechargePlan;
import com.rechargebackend.demo.Model.User;

@Repository
public interface RechargePlanRepository extends JpaRepository<RechargePlan,Long> {

    @Query("Select count(r) from RechargePlan r")
    Long findTotalPlans();
    
    @Query("SELECT r FROM RechargePlan r WHERE r.price BETWEEN :minPrice AND :maxPrice")
    List<RechargePlan> findByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

    // New query to get recharge plans for a specific user
    @Query("SELECT r FROM RechargePlan r WHERE r.user = :user")
    List<RechargePlan> findByUser(@Param("user") User user);

  
}
