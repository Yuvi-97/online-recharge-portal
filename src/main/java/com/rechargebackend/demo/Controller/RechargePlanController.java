package com.rechargebackend.demo.Controller;

import com.rechargebackend.demo.Model.RechargePlan;
import com.rechargebackend.demo.Service.RechargePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/RechargePlan")
public class RechargePlanController {

    @Autowired
    private RechargePlanService rechargePlanService;


    
    // Create a recharge plan
    @PostMapping
    public ResponseEntity<RechargePlan> createRechargePlan(@RequestBody RechargePlan plan) {
        RechargePlan createdPlan = rechargePlanService.createRechargePlan(plan);
        return ResponseEntity.status(201).body(createdPlan);
    }

    // Get all recharge plans
    @GetMapping
    public ResponseEntity<List<RechargePlan>> getAllRechargePlans() {
        return ResponseEntity.ok(rechargePlanService.getAllRechargePlans());
    }

    // Get recharge plan by ID
    @GetMapping("/{id}")
    public ResponseEntity<RechargePlan> getRechargePlanById(@PathVariable Long id) {
        Optional<RechargePlan> plan = rechargePlanService.getRechargePlanById(id);
        return plan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get total number of recharge plans
    @GetMapping("/total-plans")
    public Long getTotalPlans() {
        return rechargePlanService.getTotalPlans();
    }

    // Update recharge plan
    @PutMapping("/{id}")
    public ResponseEntity<RechargePlan> updateRechargePlan(@PathVariable Long id, @RequestBody RechargePlan updatedPlan) {
        RechargePlan plan = rechargePlanService.updateRechargePlan(id, updatedPlan);
        return plan != null ? ResponseEntity.ok(plan) : ResponseEntity.notFound().build();
    }

    // Delete recharge plan
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRechargePlan(@PathVariable Long id) {
        rechargePlanService.deleteRechargePlan(id);
        return ResponseEntity.noContent().build();
    }

    // Get recharge plans by price range
    @GetMapping("/by-price-range")
    public List<RechargePlan> getPlansByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        return rechargePlanService.getPlansByPriceRange(minPrice, maxPrice);
    }

    // Get recharge plans with pagination and sorting
    @GetMapping("/paginateSort")
    public List<RechargePlan> getRechargePlansWithPaginationAndSort(@RequestParam int page,
                                                                      @RequestParam int size,
                                                                      @RequestParam String sortBy,
                                                                      @RequestParam String sortDir) {
        return rechargePlanService.getAllRechargePlansPaginateSorting(page, size, sortBy, sortDir).getContent();
    }
}
