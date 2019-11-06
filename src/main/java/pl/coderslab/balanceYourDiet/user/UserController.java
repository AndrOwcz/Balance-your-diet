package pl.coderslab.balanceYourDiet.user;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.balanceYourDiet.dailyPlan.DailyPlanService;
import pl.coderslab.balanceYourDiet.exception.UserNotFoundException;
import pl.coderslab.balanceYourDiet.meal.MealService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@Scope("session")
@RequestMapping("/app/user")
public class UserController {

    private final UserService userService;
    private final MealService mealService;
    private final DailyPlanService dailyPlanService;

    public UserController(UserService userService, MealService mealService, DailyPlanService dailyPlanService) {
        this.userService = userService;
        this.mealService = mealService;
        this.dailyPlanService = dailyPlanService;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request, Model model) {
        UserDto userDto = getUserDto(request);
        int numberOfMeals =  mealService.findAllByUserId(userDto.getId()).size();
        int numberOfPlans = dailyPlanService.findAllByUserId(userDto.getId()).size();
        int numberOfAllMeals =  mealService.findAll().size();
        model.addAttribute("mealsCount", numberOfMeals);
        model.addAttribute("plansCount", numberOfPlans);
        model.addAttribute("allmealsCount", numberOfAllMeals);
        model.addAttribute("userDto", userDto);
        return "appDashboard";
    }

    @GetMapping("/edit")
    public String editUser(HttpServletRequest request, Model model) {
        UserDto userDto = getUserDto(request);
        model.addAttribute("userDto", userDto);
        return "appEditUserData";
    }

    @PostMapping("/edit")
    public String editUserProcessForm(@ModelAttribute("userDto") @Valid UserDto userDto, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("loginFailed", true);
            return "appEditUserData";
        }
        UserDto loggedUser = getUserDto(request);
        model.addAttribute("userDto", loggedUser);

        UserEntity userToSave = userService.mapDtoToEntity(userDto);
        UserEntity userEntityFromDb = userService.findById(loggedUser.getId()).orElseThrow(UserNotFoundException::new);
        userToSave.setPassword(userEntityFromDb.getPassword());
        userToSave.setId(userEntityFromDb.getId());
        userService.save(userToSave);

        return "redirect:dashboard";
    }

    private UserDto getUserDto(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return userService.mapEntityToDto(userService.findByUsername(username).get());
    }
}
