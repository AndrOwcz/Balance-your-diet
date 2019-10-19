package pl.coderslab.balanceYourDiet;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.balanceYourDiet.Exception.EmailNotExistException;
import pl.coderslab.balanceYourDiet.user.UserEntity;
import pl.coderslab.balanceYourDiet.user.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@Scope("session")
@RequestMapping("/")
public class HomePageController {

    private final UserRepository userRepository;

    public HomePageController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userEntity", new UserEntity());
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute("userEntity") @Valid UserEntity userEntity, BindingResult result, Model model, HttpSession session) {
        UserEntity userDb = userRepository.findByEmail(userEntity.getEmail())
                .orElseThrow(EmailNotExistException::new);
        if (!userEntity.getPassword().equals(userDb.getPassword())) {
//        boolean passwordMiss = !BCrypt.checkpw(userEntity.getPassword(), userDb.getPassword());
//        if (result.hasErrors() || passwordMiss) {
            model.addAttribute("loginFailed", true);
            return "login";
        } else {
            session.setAttribute("authorizedUser", userDb);
            return "redirect:user/dashboard";
        }
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        model.addAttribute("userEntity", new UserEntity());
        return "registerForm";
    }

    @PostMapping("/register")
    public String processRegisterForm(@ModelAttribute("userEntity") @Valid UserEntity userEntity, BindingResult result) {
        if (result.hasErrors()) {
            return "registerForm";
        }
        userRepository.save(userEntity);
        return "home";
    }


}
