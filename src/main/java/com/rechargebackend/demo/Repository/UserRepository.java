package com.rechargebackend.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rechargebackend.demo.Model.User;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByEmail(String email);

    @Query("SELECT COUNT(u) FROM User u")
    Long totalUsers();

}