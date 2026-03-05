package com.example.ecommerce.repository;

import com.example.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Laravel এর User::where('email', $email)->first() এর মতো
    Optional<User> findByEmail(String email);

    // email already আছে কিনা check — Laravel এর exists() এর মতো
    boolean existsByEmail(String email);
}