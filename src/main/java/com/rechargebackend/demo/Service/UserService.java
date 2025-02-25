package com.rechargebackend.demo.Service;
import com.rechargebackend.demo.Model.*;
import com.rechargebackend.demo.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists with email: " + user.getEmail());
        }

        return userRepository.save(user);
    }
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Page<User> getUsersWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size); // No sorting applied
        return userRepository.findAll(pageable);
    }
    
    public Page<User> getUserWithPaginationandSort(int page,int size,String sortBy,String sortDir){
        Sort sort= sortDir.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(page,size,sort);
        return userRepository.findAll(pageable);
    }


    public Optional<User> updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setAddress(updatedUser.getAddress());
            user.setFirstName(updatedUser.getFirstName());
            user.setLastname(updatedUser.getLastname());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
    
            return Optional.of(userRepository.save(user)); 
        }).orElse(Optional.empty()); 
    }
    
    public Long getTotalUsers() {
        return userRepository.totalUsers();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
