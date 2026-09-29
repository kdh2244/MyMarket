package com.example.market.Controller;

import com.example.market.dto.PresignedUrlResponse;
import com.example.market.dto.UserDto;
import com.example.market.dto.UserRespondDto;
import com.example.market.entity.User;
import com.example.market.service.S3Service;
import com.example.market.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity addUser(UserDto userDto){
        User user = userDto.toEntity();
        System.out.println(user);
        userService.save(user);
        return ResponseEntity.ok("user 저장 완료");
    }

}
