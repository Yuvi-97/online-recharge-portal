package com.rechargebackend.demo.Controller;

import com.rechargebackend.demo.Model.*;
import com.rechargebackend.demo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/count")
    public Long getTotalUsers() {
        return userService.getTotalUsers();
    }
    @GetMapping("/paginateSorting")
    public Page<User> getUsersWithPaginateSortString(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size,
                                                 @RequestParam(defaultValue = "name") String sortBy,
                                                 @RequestParam(defaultValue = "asc") String sortDir) {
        return userService.getUserWithPaginationandSort(page, size, sortBy, sortDir);
    }
    
    @GetMapping("/by-address")
    public List<User> getUsersByAddress(@RequestParam String address) {
        return userService.getUserbyAddress(address);
    }
    
    @GetMapping("/paginate")
    public Page<User> getUsersWithPagination(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {
        return userService.getUsersWithPagination(page, size);
    }
    
    @PutMapping("/{id}")
    public Optional<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
