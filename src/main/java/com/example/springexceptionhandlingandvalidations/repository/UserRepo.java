package com.example.springexceptionhandlingandvalidations.repository;

import com.example.springexceptionhandlingandvalidations.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepo extends JpaRepository<User,Long> {
}
