package com.rechargebackend.demo.Controller;

import com.rechargebackend.demo.Model.User;
import com.rechargebackend.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create a new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get total user count
    @GetMapping("/count")
    public Long getTotalUsers() {
        return userService.getTotalUsers();
    }

    // Get users by address
    @GetMapping("/by-address")
    public List<User> getUsersByAddress(@RequestParam String address) {
        return userService.getUserbyAddress(address);
    }

    // Get users by phone number
    @GetMapping("/by-phone")
    public List<User> getUsersByPhoneNumber(@RequestParam String phoneNumber) {
        return userService.getUsersByPhoneNumber(phoneNumber);
    }

    // Pagination and sorting
    @GetMapping("/paginate")
    public Page<User> getUsersWithPagination(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {
        return userService.getUsersWithPagination(page, size);
    }

    @GetMapping("/paginateSorting")
    public Page<User> getUsersWithPaginateSort(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "5") int size,
                                                @RequestParam(defaultValue = "name") String sortBy,
                                                @RequestParam(defaultValue = "asc") String sortDir) {
        return userService.getUserWithPaginationandSort(page, size, sortBy, sortDir);
    }

    // Update user
    @PutMapping("/{id}")
    public Optional<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
