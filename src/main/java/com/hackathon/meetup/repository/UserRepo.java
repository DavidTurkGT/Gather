package com.hackathon.meetup.repository;

import com.hackathon.meetup.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {
}
