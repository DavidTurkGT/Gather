package com.hackathon.meetup.repository;

import com.hackathon.meetup.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
