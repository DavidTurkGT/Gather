package com.hackathon.meetup.service;

import com.hackathon.meetup.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
     User addUser(User newUser);
     User findById(int id);
}
