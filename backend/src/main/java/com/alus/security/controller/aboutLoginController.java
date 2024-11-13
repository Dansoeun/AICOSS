package com.alus.security.controller;

import com.alus.security.dto.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class aboutLoginController {

    @GetMapping("/aboutLogin")
    public String aboutLP(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        if(userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }
        return "aboutLogin";
    }
}
