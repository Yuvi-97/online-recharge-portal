package com.rechargebackend.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rechargebackend.demo.Model.Rechargeplan;

import jakarta.persistence.Entity;

@Entity
public interface RechargeplanRepository extends JpaRepository<Rechargeplan,Long>{
}
