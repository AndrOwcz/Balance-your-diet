package pl.coderslab.balanceYourDiet.dailyPlan;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.balanceYourDiet.meal.MealService;
import pl.coderslab.balanceYourDiet.user.UserDto;
import pl.coderslab.balanceYourDiet.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@Scope("session")
@RequestMapping("/app/plan")
public class DailyPlanController {

    private final DailyPlanService dailyPlanService;
    private final MealService mealService;
    private final UserService userService;

    public DailyPlanController(DailyPlanService dailyPlanService, MealService mealService, UserService userService) {
        this.dailyPlanService = dailyPlanService;
        this.mealService = mealService;
        this.userService = userService;
    }

    @GetMapping("/list")
    public String dailyPlanList(HttpServletRequest request, Model model) {
        UserDto authorizedUserDto = getUserDto(request);
        Long id = authorizedUserDto.getId();
        model.addAttribute("allPlans", dailyPlanService.findAllById(id));
        return "appPlanList";
    }

    @GetMapping("/add")
    public String dashboard(HttpServletRequest request, Model model) {
        UserDto authorizedUserDto = getUserDto(request);
        Long id = authorizedUserDto.getId();
        model.addAttribute("meals", mealService.mapMealListEntityToDto(mealService.findAllByUserId(id)));
        model.addAttribute("plan", new DailyPlanDto());
        return "appAddNewPlan";
    }

    //todo postmapping

    private UserDto getUserDto(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return userService.mapEntityToDto(userService.findByUsername(username).get());

    }
}
