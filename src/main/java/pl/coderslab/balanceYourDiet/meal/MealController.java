package pl.coderslab.balanceYourDiet.meal;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.balanceYourDiet.user.UserDto;
import pl.coderslab.balanceYourDiet.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@Scope("session")
@RequestMapping("/app/meal")
public class MealController {

    private final MealService mealService;
    private final UserService userService;

    public MealController(MealService mealService, UserService userService) {
        this.mealService = mealService;
        this.userService = userService;
    }

    @GetMapping("/list")
    public String mealList(HttpServletRequest request, Model model) {
        UserDto authorizedUserDto = getUserDto(request);
        Long id = authorizedUserDto.getId();
        List<MealEntity> userMealsEntity = mealService.findAllByUserId(id);
        List<MealDto> userMealsDto = mealService.mapMealListEntityToDto(userMealsEntity);
        model.addAttribute("allMeals", userMealsDto);
        return "appMealList";
    }

    @GetMapping("/add")
    public String dashboard(HttpServletRequest request, Model model) {
        UserDto userDto = getUserDto(request);
        model.addAttribute("userDto", userDto);
        return "appAddNewMeal";
    }

    //todo postmapping

    private UserDto getUserDto(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return userService.mapEntityToDto(userService.findByUsername(username).get());

    }
}
