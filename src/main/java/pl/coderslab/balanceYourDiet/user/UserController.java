package pl.coderslab.balanceYourDiet.user;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@Scope("session")
@RequestMapping("/app/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        UserDto userDto = (UserDto) session.getAttribute("authorizedUser");
        model.addAttribute("authorizedUser", userDto);
        return "dashboard";
    }


}
