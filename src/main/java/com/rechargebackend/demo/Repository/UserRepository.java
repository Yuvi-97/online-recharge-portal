package com.rechargebackend.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rechargebackend.demo.Model.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByEmail(String email);

    @Query("SELECT COUNT(u) FROM User u")
    Long totalUsers();

    @Query("SELECT u FROM User u WHERE u.address LIKE %:address%")
    List<User> findUsersByAddress(@Param("address") String address);

}