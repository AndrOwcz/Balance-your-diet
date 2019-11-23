package pl.coderslab.balanceYourDiet;

import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.balanceYourDiet.user.UserDto;
import pl.coderslab.balanceYourDiet.user.UserEntity;
import pl.coderslab.balanceYourDiet.user.UserService;

import javax.validation.Valid;

@Controller
@Scope("session")
@RequestMapping("/")
public class HomePageController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public HomePageController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
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
        userDto.setRequiredCalories(0L);
        userDto.setRequiredCarbs(0L);
        userDto.setRequiredFats(0L);
        userDto.setRequiredProtein(0L);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserEntity userToSave = userService.mapDtoToEntity(userDto);
        userToSave.setPassword(userDto.getPassword());
        userToSave.setEnabled(true);
        userService.save(userToSave);
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
}
