package com.amp.apiyizazun.repositories;

import org.springframework.data.repository.CrudRepository;

import com.amp.apiyizazun.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
