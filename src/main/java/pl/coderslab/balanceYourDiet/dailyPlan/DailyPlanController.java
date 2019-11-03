package pl.coderslab.balanceYourDiet.dailyPlan;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.balanceYourDiet.exception.MealNotFoundException;
import pl.coderslab.balanceYourDiet.exception.PlanNotFoundException;
import pl.coderslab.balanceYourDiet.exception.UserNotFoundException;
import pl.coderslab.balanceYourDiet.meal.MealDto;
import pl.coderslab.balanceYourDiet.meal.MealEntity;
import pl.coderslab.balanceYourDiet.meal.MealService;
import pl.coderslab.balanceYourDiet.user.UserDto;
import pl.coderslab.balanceYourDiet.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
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
        UserDto authorizedUserDto = fetchUserDto(request);
        model.addAttribute("userDto", authorizedUserDto);
        Long id = authorizedUserDto.getId();
        model.addAttribute("allPlans", dailyPlanService.findAllByUserId(id));
        return "appPlanList";
    }

    @GetMapping("/add")
    public String addPlan(HttpServletRequest request, Model model) {
        UserDto authorizedUserDto = fetchUserDto(request);
        model.addAttribute("userDto", authorizedUserDto);
        model.addAttribute("planDto", new DailyPlanDto());
        return "appAddNewPlan";
    }

    @PostMapping(value = "/add")
    public String addPlanProcessForm(@ModelAttribute("planDto") @Valid DailyPlanDto dailyPlanDto, BindingResult result, Model model, HttpServletRequest request) {
        UserDto loggedUser = fetchUserDto(request);
        model.addAttribute("userDto", loggedUser);
        Long id = loggedUser.getId();
        DailyPlanEntity dailyPlanEntity = dailyPlanService.mapDtoToEntity(dailyPlanDto);

        List<MealEntity> mealsToAdd = new ArrayList<>();
        for (MealDto mealDto : dailyPlanDto.getMealDtos()) {
            MealEntity mealEntity = mealService.findById(mealDto.getId()).orElseThrow(MealNotFoundException::new);
            mealsToAdd.add(mealEntity);
        }
        dailyPlanEntity.setMealEntities(mealsToAdd);

        dailyPlanEntity.setUserEntity(userService.findById(id).orElseThrow(UserNotFoundException::new));
        dailyPlanService.save(dailyPlanEntity);
        return "redirect:list";
    }

    @GetMapping("/details/{id}")
    public String dailyPlanDetails(HttpServletRequest request, Model model, @PathVariable Long id) {
        setUserDtoAsModelAttribute(request, model);
        model.addAttribute("planDto", dailyPlanService.mapEntityToDto(dailyPlanService.findById(id).orElseThrow(PlanNotFoundException::new)));

        List<Long> mealIds = dailyPlanService.findAllMealEntitiesIdByDailyPlanId(id);
        List<MealEntity> mealsToAdd = new ArrayList<>();

        for (Long mealId : mealIds) {
            MealEntity mealEntity = mealService.findById(mealId).orElseThrow(MealNotFoundException::new);
            mealsToAdd.add(mealEntity);
        }
        model.addAttribute("mealsInPlan", mealService.mapMealListEntityToDto(mealsToAdd));
        return "appPlanDetails";
    }

    @GetMapping("/edit/{id}")
    public String dailyPlanEdit(HttpServletRequest request, Model model, @PathVariable Long id) {
        setUserDtoAsModelAttribute(request, model);
        model.addAttribute("planDto", dailyPlanService.mapEntityToDto(dailyPlanService.findById(id).orElseThrow(PlanNotFoundException::new)));
        return "appEditPlan";
    }

    @PostMapping("/edit/{id}")
    public String editPlanProcessForm(@ModelAttribute("planDto") @Valid DailyPlanDto dailyPlanDto, BindingResult result, Model model, HttpServletRequest request, @PathVariable Long id) {
        UserDto loggedUser = fetchUserDto(request);
        model.addAttribute("userDto", loggedUser);
        Long userId = loggedUser.getId();

        DailyPlanEntity dailyPlanEntity = dailyPlanService.mapDtoToEntity(dailyPlanDto);

        List<MealEntity> mealsToAdd = new ArrayList<>();
        for (MealDto mealDto : dailyPlanDto.getMealDtos()) {
            MealEntity mealEntity = mealService.findById(mealDto.getId()).orElseThrow(MealNotFoundException::new);
            mealsToAdd.add(mealEntity);
        }
        dailyPlanEntity.setMealEntities(mealsToAdd);

        dailyPlanEntity.setUserEntity(userService.findById(userId).orElseThrow(UserNotFoundException::new));
        dailyPlanEntity.setId(id);
        dailyPlanService.save(dailyPlanEntity);
        return "redirect:../list";
    }

    @GetMapping("/remove/meal/{planId}/{mealId}")
    public String removeMealFromPlan(@PathVariable Long planId, @PathVariable Long mealId) {

        DailyPlanEntity dailyPlanEntity = dailyPlanService.findById(planId).orElseThrow(PlanNotFoundException::new);
        dailyPlanEntity.getMealEntities().removeIf(mealEntity -> mealEntity.getId().equals(mealId));
        dailyPlanService.save(dailyPlanEntity);
        return "redirect:../../../list";
    }

    @GetMapping("/delete/{id}")
    public String dailyPlanDelete(HttpServletRequest request, Model model, @PathVariable Long id) {
        setUserDtoAsModelAttribute(request, model);
        dailyPlanService.deleteById(id);
        return "redirect:../list";
    }

    @ModelAttribute("meals")
    public List<MealDto> fetchAllUserMeals(HttpServletRequest request) {
        UserDto authorizedUserDto = fetchUserDto(request);
        Long id = authorizedUserDto.getId();
        return mealService.mapMealListEntityToDto(mealService.findAllByUserId(id));
    }

    private UserDto fetchUserDto(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return userService.mapEntityToDto(userService.findByUsername(username).orElseThrow(UserNotFoundException::new));
    }

    private void setUserDtoAsModelAttribute(HttpServletRequest request, Model model) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        model.addAttribute("userDto", userService.mapEntityToDto(userService.findByUsername(username).orElseThrow(UserNotFoundException::new)));
    }
}
