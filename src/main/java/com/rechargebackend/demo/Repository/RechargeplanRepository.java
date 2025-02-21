package com.rechargebackend.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rechargebackend.demo.Model.RechargePlan;

@Repository
public interface RechargePlanRepository extends JpaRepository<RechargePlan,Long>{
    
}
