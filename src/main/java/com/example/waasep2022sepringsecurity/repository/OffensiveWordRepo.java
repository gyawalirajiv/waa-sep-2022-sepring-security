package com.example.waasep2022sepringsecurity.repository;

import com.example.waasep2022sepringsecurity.aspects.UserOffensiveCount;
import com.example.waasep2022sepringsecurity.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OffensiveWordRepo extends CrudRepository<UserOffensiveCount, Integer> {
    Optional<UserOffensiveCount> findByUser(User user);
}
