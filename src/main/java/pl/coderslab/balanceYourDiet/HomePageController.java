package pl.coderslab.balanceYourDiet;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.balanceYourDiet.exception.EmailNotExistException;
import pl.coderslab.balanceYourDiet.user.UserDto;
import pl.coderslab.balanceYourDiet.user.UserEntity;
import pl.coderslab.balanceYourDiet.user.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@Scope("session")
@RequestMapping("/")
public class HomePageController {

    private final UserService userService;

    public HomePageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute("userDto") @Valid UserDto userDto, BindingResult result, Model model, HttpSession session) {
        UserEntity userDb = userService.findByEmail(userDto.getEmail())
                .orElseThrow(EmailNotExistException::new);
        if (!userDto.getPassword().equals(userDb.getPassword())) {
//        boolean passwordMiss = !BCrypt.checkpw(userEntity.getPassword(), userDb.getPassword());
//        if (result.hasErrors() || passwordMiss) {
            model.addAttribute("loginFailed", true);
            return "login";
        } else {
            UserDto authorizedUserDto = userService.mapEntityToDto(userDb);
            session.setAttribute("authorizedUser", authorizedUserDto);
            return "redirect:app/user/dashboard";
        }
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "registerForm";
    }

    @PostMapping("/register")
    public String processRegisterForm(@ModelAttribute("userDto") @Valid UserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            return "registerForm";
        }
        userService.save(userService.mapDtoToEntity(userDto));
        return "home";
    }


}
