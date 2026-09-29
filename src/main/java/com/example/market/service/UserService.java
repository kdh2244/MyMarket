package com.example.market.service;

import com.example.market.entity.User;
import com.example.market.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUser(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 객체입니다."));
    }

    public void save(User user){
        System.out.println("userService : "+user);
        userRepository.save(user);
    }

}
