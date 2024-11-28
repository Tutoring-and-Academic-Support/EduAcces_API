package com.upao.eduaccess.controller;

import com.upao.eduaccess.dto.UserProfileDTO;
import com.upao.eduaccess.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController("userProfileController")
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping
    public UserProfileDTO getProfile(Authentication authentication) {
        String email = authentication.getName();
        return userService.getUserProfile(email);
    }

    @PutMapping
    public UserProfileDTO updateProfile(@RequestBody UserProfileDTO userProfileDTO, Authentication authentication) {
        String email = authentication.getName();
        return userService.updateUserProfile(email, userProfileDTO);
    }
}
