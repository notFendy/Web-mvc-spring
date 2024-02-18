package uz.pdp.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.dao.UserDao;
import uz.pdp.dto.UserLoginDto;
import uz.pdp.dto.UserSignUpDto;
import uz.pdp.model.User;



@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserDao userDao;
    @GetMapping
    public String auth(Model model) {
        model.addAttribute("dto", new UserSignUpDto());
        return "auth";
    }

    @PostMapping("/signup")
    public ModelAndView SignUp(
            @Valid @ModelAttribute("dto") UserSignUpDto signupDto,
            ModelAndView modelAndView,
            BindingResult bindingResult
    ){


        if (isEmailExists(signupDto.getEmail())){
            modelAndView.setViewName("error");
            return modelAndView;
        }

        if (bindingResult.hasErrors()){
            modelAndView.setViewName("auth");
            return modelAndView;
        }

        final var user = User.builder()
                .username(signupDto.getUsername())
                .email(signupDto.getEmail())
                .password(signupDto.getPassword())
                .gender(signupDto.getGender())
                .build();

        userDao.save(user);

        modelAndView.addObject("user", user);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping ("/login")
    public ModelAndView login(@ModelAttribute UserLoginDto userLoginDto,
                              ModelAndView modelAndView
    )  {

        User user = getUserByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword());

        if (user != null) {
            modelAndView.setViewName("project");
        } else {
            modelAndView.setViewName("login-error");
        }

        return modelAndView;
    }

    private User getUserByEmailAndPassword(String email, String password) {
        try {
            User user = userDao.findByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                return user;
            } else {
                return null;
            }
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private boolean isEmailExists(String email) {
        try {
            User existingUser = userDao.findByEmail(email);
            return existingUser != null;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
