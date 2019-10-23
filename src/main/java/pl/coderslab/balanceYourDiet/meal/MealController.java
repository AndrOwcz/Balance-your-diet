package pl.coderslab.balanceYourDiet.meal;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.balanceYourDiet.user.UserDto;

import javax.servlet.http.HttpSession;

@Controller
@Scope("session")
@RequestMapping("/app/meal")
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/list")
    public String dashboard(HttpSession session, Model model) {
        UserDto userDto = (UserDto) session.getAttribute("authorizedUser");
        Long id = userDto.getId();
        model.addAttribute("allMeals", mealService.findAllById(id));
        return "appMealList";
    }

}
