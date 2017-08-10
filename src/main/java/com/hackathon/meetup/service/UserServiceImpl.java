package com.hackathon.meetup.service;

import com.hackathon.meetup.domain.User;
import com.hackathon.meetup.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Transactional
    @Override
    public User addUser(User newUser) {
        return userRepo.save( newUser );
    }
}
