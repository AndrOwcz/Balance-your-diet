package pl.coderslab.balanceYourDiet.dailyPlan;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.balanceYourDiet.exception.UserNotFoundException;
import pl.coderslab.balanceYourDiet.meal.MealDto;
import pl.coderslab.balanceYourDiet.meal.MealService;
import pl.coderslab.balanceYourDiet.user.UserDto;
import pl.coderslab.balanceYourDiet.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

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
        model.addAttribute("userDto", authorizedUserDto);
        Long id = authorizedUserDto.getId();
        model.addAttribute("allPlans", dailyPlanService.findAllById(id));
        return "appPlanList";
    }

    @GetMapping("/add")
    public String addPlan(HttpServletRequest request, Model model) {
        UserDto authorizedUserDto = getUserDto(request);
        model.addAttribute("userDto", authorizedUserDto);
        model.addAttribute("planDto", new DailyPlanDto());
        return "appAddNewPlan";
    }


    @PostMapping("/add")
    public String addPlanProcessForm(@ModelAttribute("planDto") @Valid DailyPlanDto dailyPlanDto, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("loginFailed", true);
            return "appAddNewPlan";
        }
        UserDto loggedUser = getUserDto(request);
        model.addAttribute("userDto", loggedUser);
        Long id = loggedUser.getId();
        DailyPlanEntity dailyPlanEntity = dailyPlanService.mapDtoToEntity(dailyPlanDto);
        dailyPlanEntity.setMealEntities(mealService.mapListDtoToEntity(dailyPlanDto.getMealDtos()));
        dailyPlanEntity.setUserEntity(userService.findById(id).orElseThrow(UserNotFoundException::new));
        dailyPlanService.save(dailyPlanEntity);
        return "redirect:list";
    }


    @ModelAttribute("meals")
    public List<MealDto> fetchAllMeals(HttpServletRequest request) {
        UserDto authorizedUserDto = getUserDto(request);
        Long id = authorizedUserDto.getId();
        return mealService.mapMealListEntityToDto(mealService.findAllByUserId(id));
    }


    private UserDto getUserDto(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return userService.mapEntityToDto(userService.findByUsername(username).orElseThrow(UserNotFoundException::new));
    }
}
