package com.smart.controller;

import com.smart.entities.User;
import com.smart.helper.Message;
import com.smart.repository.UserRepository;
//import jakarta.servlet.http.HttpSession;


import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

//import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

//    @GetMapping("/test")
//    @ResponseBody
//    public String test(){
//        User user = new User();
//        user.setName("Raj");
//        user.setEmail("Raj@gmail.com");
//        userRepository.save(user);
//
//        return "Working";
//    }

    @RequestMapping("/")
    public String home(Model model){                      //Model helps the send the data in template
        model.addAttribute("title","Home -smart contact manager");
        return "home";
    }

    @RequestMapping("/about")
    public String about(Model model){                      //Model helps the send the data in template
        model.addAttribute("title","About -smart contact manager");
        return "about";
    }

    @RequestMapping("/signup")
    public String signup(Model model){                      //Model helps the send the data in template
        model.addAttribute("title","Register -smart contact manager");
        model.addAttribute("user",new User());
        return "signup";
    }

    //handler for registering user
//    @RequestMapping(value = "/do_register",method = RequestMethod.POST)
    @PostMapping("/do_register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result1,
                               @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,
                               HttpSession session){

        try {
            if (!agreement)
            {
                System.out.println("you have not agreed the term and condition");
                throw new Exception("you have not agreed the term and condition");
            }

            if (result1.hasErrors())
            {
                System.out.println("ERROR " +result1.toString());
                model.addAttribute("user",user);
                return "signup";
            }
            user.setRole("ROLE_USER");
            user.setEnabled(true);
            user.setImageUrl("default.png");
            user.setPassword(passwordEncoder.encode(user.getPassword()));


            System.out.println("Agreement "+ agreement);
            System.out.println("USER"+user);

            User result = this.userRepository.save(user);

            model.addAttribute("user",new User());
            session.setAttribute("message",new Message("Successfully Registered !!","alert-success"));
            return "signup";

        }catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("user", user);
            session.setAttribute("message", new Message("Something Went wrong !!" + e.getMessage(), "alert-danger"));

            return "signup";
        }
    }

    //handler for custom login

    @GetMapping("/signin")
    public String customLogin(Model model)
    {
        model.addAttribute("title","Login Page");

        return "login";
    }
}

