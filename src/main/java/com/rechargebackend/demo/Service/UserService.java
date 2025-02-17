package com.rechargebackend.demo.Service;
import com.rechargebackend.demo.Model.*;
import com.rechargebackend.demo.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
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
    

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
