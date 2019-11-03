package pl.coderslab.balanceYourDiet.meal;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.balanceYourDiet.comment.CommentEntity;
import pl.coderslab.balanceYourDiet.comment.CommentService;
import pl.coderslab.balanceYourDiet.dailyPlan.DailyPlanEntity;
import pl.coderslab.balanceYourDiet.dailyPlan.DailyPlanService;
import pl.coderslab.balanceYourDiet.exception.CommentNotFoundException;
import pl.coderslab.balanceYourDiet.exception.MealNotFoundException;
import pl.coderslab.balanceYourDiet.exception.PlanNotFoundException;
import pl.coderslab.balanceYourDiet.exception.UserNotFoundException;
import pl.coderslab.balanceYourDiet.user.UserDto;
import pl.coderslab.balanceYourDiet.user.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("session")
@RequestMapping("/app/meal")
public class MealController {

    private final MealService mealService;
    private final UserService userService;
    private final CommentService commentService;
    private final DailyPlanService dailyPlanService;

    public MealController(MealService mealService, UserService userService, CommentService commentService, DailyPlanService dailyPlanService) {
        this.mealService = mealService;
        this.userService = userService;
        this.commentService = commentService;
        this.dailyPlanService = dailyPlanService;
    }

    @GetMapping("/list")
    public String mealList(HttpServletRequest request, Model model) {
        UserDto authorizedUserDto = fetchUserDto(request);
        Long id = authorizedUserDto.getId();
        model.addAttribute("userDto", authorizedUserDto);
        List<MealEntity> userMealsEntity = mealService.findAllByUserId(id);
        List<MealDto> userMealsDto = mealService.mapMealListEntityToDto(userMealsEntity);
        model.addAttribute("allMeals", userMealsDto);
        return "appMealList";
    }

    @GetMapping("/add")
    public String dashboard(HttpServletRequest request, Model model) {
        setUserDtoAsModelAttribute(request, model);
        return "appAddNewMeal";
    }

    //todo postmapping

    @GetMapping("/details/{id}")
    public String mealDetails(HttpServletRequest request, Model model, @PathVariable Long id) {
        setUserDtoAsModelAttribute(request, model);
        model.addAttribute("mealDto", mealService.mapEntityToDto(mealService.findById(id).orElseThrow(MealNotFoundException::new)));

        List<Long> commentIds = mealService.findAllCommentEntitiesIdByMealId(id);
        List<CommentEntity> commentsToAdd = new ArrayList<>();

        for (Long commentId : commentIds) {
            CommentEntity commentEntity = commentService.findById(commentId).orElseThrow(CommentNotFoundException::new);
            commentsToAdd.add(commentEntity);
        }
        model.addAttribute("commentsOfMeal", commentService.mapCommentListEntityToDto(commentsToAdd));
        return "appMealDetails";
    }

    @GetMapping("/delete/{id}")
    public String mealDelete(HttpServletRequest request, Model model, @PathVariable Long id) {
        setUserDtoAsModelAttribute(request, model);

        List<Long> dailyPlanIdsByMealId = mealService.findAllDailyPlanIdsByMealId(id);
        if (!dailyPlanIdsByMealId.isEmpty()) {
            for (Long dailyPlanId : dailyPlanIdsByMealId) {
                DailyPlanEntity dailyPlanEntity = dailyPlanService.findById(dailyPlanId).orElseThrow(PlanNotFoundException::new);
                List<Long> mealIds = dailyPlanService.findAllMealEntitiesIdByDailyPlanId(dailyPlanEntity.getId());
                //todo
//                dailyPlanEntity.setMealEntities();
                model.addAttribute("isAssigned", true);
            }
        } else {
            mealService.deleteById(id);
        }
        return "redirect:../list";
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
