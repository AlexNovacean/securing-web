package com.example.securingweb;

import com.example.securingweb.model.User;
import com.example.securingweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") User user,
                                      BindingResult result) {

        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            return "registration";
        }
        try {
            userService.save(user);
        } catch (Exception e) {
            // log something
        }
        return "redirect:/login";
    }

    @GetMapping("/login/**")
    public String login() {
        return "login";
    }
}
