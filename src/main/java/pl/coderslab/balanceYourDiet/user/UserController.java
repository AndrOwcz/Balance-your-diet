package pl.coderslab.balanceYourDiet.user;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.balanceYourDiet.exception.UserNotFoundException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
        return "appDashboard";
    }

    @GetMapping("/edit")
    public String editUser(HttpSession session, Model model) {
        UserDto userDto = (UserDto) session.getAttribute("authorizedUser");
        model.addAttribute("userDto", userDto);
        return "appEditUserData";
    }

    @PostMapping("/edit")
    public String editUserProcessForm(@ModelAttribute("userDto") @Valid UserDto userDto, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            model.addAttribute("loginFailed", true);
            return "appEditUserData";
        }

        UserDto loggedUser = (UserDto) session.getAttribute("authorizedUser");
        userDto.setId(loggedUser.getId());
        userDto.setMealDto(loggedUser.getMealDto());
        userDto.setDailyPlanDtos(loggedUser.getDailyPlanDtos());
        UserEntity userEntityFromDb = userService.findById(loggedUser.getId()).orElseThrow(UserNotFoundException::new);
        userDto.setPassword(userEntityFromDb.getPassword());
        userService.save(userService.mapDtoToEntity(userDto));

        session.setAttribute("authorizedUser", userDto);
        return "appDashboard";
    }


}
