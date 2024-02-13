package uz.pdp.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.dto.UserSignUpDto;
import uz.pdp.model.User;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private static final List<User> USERS = new ArrayList<>();

    @GetMapping
    public String auth(){
        return "auth";
    }
    @PostMapping("/signup")
    public ModelAndView SignUp(
            @ModelAttribute UserSignUpDto signupDto,
            ModelAndView modelAndView
    ){
        if (isEmailExists(signupDto.email())){
            modelAndView.setViewName("error");
            return modelAndView;
        }

        final var user = User.builder()
                .username(signupDto.username())
                .email(signupDto.email())
                .password(signupDto.password())
                .gender(signupDto.gender())
                .build();

        USERS.add(user);

        System.out.println(USERS);

        modelAndView.addObject("user", user);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping ("/login")
    public ModelAndView login(@ModelAttribute UserSignUpDto userSignUpDto,
                              ModelAndView modelAndView
    )  {

        User user = getUserByEmailAndPassword(userSignUpDto.email(), userSignUpDto.password());

        if (user != null) {

            modelAndView.setViewName("project");

        } else {
            modelAndView.setViewName("login-error");
        }

        return modelAndView;
    }

    private User getUserByEmailAndPassword(String email, String password) {
        for (User user : USERS) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private boolean isEmailExists(String email) {
        for (User user : USERS) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

}
