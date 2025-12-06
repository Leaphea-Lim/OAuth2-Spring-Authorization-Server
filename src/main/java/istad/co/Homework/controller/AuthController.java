package istad.co.Homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    //todo: for displaying the login page
    @GetMapping("/login")
    public String login() {
        return "login";
    }

}