package com.advancedmobileprogramming.yizazunapi.repositories;

import com.advancedmobileprogramming.yizazunapi.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}