package pl.coderslab.balanceYourDiet.dailyPlan;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.balanceYourDiet.meal.MealService;
import pl.coderslab.balanceYourDiet.user.UserDto;

import javax.servlet.http.HttpSession;

@Controller
@Scope("session")
@RequestMapping("/app/plan")
public class DailyPlanController {

    private final DailyPlanService dailyPlanService;
    private final MealService mealService;

    public DailyPlanController(DailyPlanService dailyPlanService, MealService mealService) {
        this.dailyPlanService = dailyPlanService;
        this.mealService = mealService;
    }

    @GetMapping("/list")
    public String dailyPlanList(HttpSession session, Model model) {
        UserDto authorizedUserDto = (UserDto) session.getAttribute("authorizedUser");
        Long id = authorizedUserDto.getId();
        model.addAttribute("allPlans", dailyPlanService.findAllById(id));
        return "appPlanList";
    }

    @GetMapping("/add")
    public String dashboard(HttpSession session, Model model) {
        UserDto authorizedUserDto = (UserDto) session.getAttribute("authorizedUser");
        session.setAttribute("authorizedUser", authorizedUserDto);
        Long id = authorizedUserDto.getId();
        model.addAttribute("meals", mealService.mapMealListEntityToDtoNoRelations(mealService.findAllByUserId(id)));
        model.addAttribute("plan", new DailyPlanDto());
        return "appAddNewPlan";
    }

    //todo postmapping

}
