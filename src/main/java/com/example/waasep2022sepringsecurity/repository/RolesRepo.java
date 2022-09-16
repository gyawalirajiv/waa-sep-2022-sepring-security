package com.example.waasep2022sepringsecurity.repository;

import com.example.waasep2022sepringsecurity.entity.Role;
import com.example.waasep2022sepringsecurity.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepo extends CrudRepository<Role, Integer> {
}
