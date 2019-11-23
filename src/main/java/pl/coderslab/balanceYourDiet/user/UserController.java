package pl.coderslab.balanceYourDiet.user;

import org.springframework.security.crypto.password.PasswordEncoder;
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
import pl.coderslab.balanceYourDiet.product.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/app/user")
public class UserController {

    private final UserService userService;
    private final MealService mealService;
    private final DailyPlanService dailyPlanService;
    private final ProductService productService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, MealService mealService, DailyPlanService dailyPlanService, ProductService productService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.mealService = mealService;
        this.dailyPlanService = dailyPlanService;
        this.productService = productService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request, Model model) {
        UserDto userDto = getUserDto(request);
        int numberOfMeals = mealService.findAllByUserId(userDto.getId()).size();
        int numberOfPlans = dailyPlanService.findAllByUserId(userDto.getId()).size();
        int numberOfAllMeals = mealService.findAll().size();
        int numberOfProducts = productService.findAll().size();
        model.addAttribute("mealsCount", numberOfMeals);
        model.addAttribute("plansCount", numberOfPlans);
        model.addAttribute("allMealsCount", numberOfAllMeals);
        model.addAttribute("numberOfProducts", numberOfProducts);
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

    @GetMapping("/edit/password")
    public String editPassword(HttpServletRequest request, Model model) {
        UserDto userDto = getUserDto(request);
        model.addAttribute("userDto", userDto);
        return "appEditPassword";
    }

    @PostMapping("/edit/password")
    public String editPasswordProcessForm(@ModelAttribute("userDto") UserDto userDto,
                                          Model model, HttpServletRequest request) {
        UserDto loggedUser = getUserDto(request);
        model.addAttribute("userDto", loggedUser);

        UserEntity userEntity = userService.findById(loggedUser.getId()).orElseThrow(UserNotFoundException::new);

        if (passwordEncoder.matches(userDto.getPassword(), userEntity.getPassword())) {
            userEntity.setPassword(passwordEncoder.encode(userDto.getNewPassword()));
            userService.save(userEntity);
            return "redirect:../dashboard";
        }
        model.addAttribute("editPasswordDataFailed", true);
        return "appEditPassword";
    }

    private UserDto getUserDto(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return userService.mapEntityToDto(userService.findByUsername(username).orElseThrow(UserNotFoundException::new));
    }
}
