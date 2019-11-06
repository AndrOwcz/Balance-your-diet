package pl.coderslab.balanceYourDiet.meal;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.balanceYourDiet.comment.CommentEntity;
import pl.coderslab.balanceYourDiet.comment.CommentService;
import pl.coderslab.balanceYourDiet.dailyPlan.DailyPlanEntity;
import pl.coderslab.balanceYourDiet.dailyPlan.DailyPlanService;
import pl.coderslab.balanceYourDiet.exception.*;
import pl.coderslab.balanceYourDiet.product.ProductDto;
import pl.coderslab.balanceYourDiet.product.ProductService;
import pl.coderslab.balanceYourDiet.productPortion.ProductPortionDto;
import pl.coderslab.balanceYourDiet.productPortion.ProductPortionEntity;
import pl.coderslab.balanceYourDiet.productPortion.ProductPortionService;
import pl.coderslab.balanceYourDiet.user.UserDto;
import pl.coderslab.balanceYourDiet.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
    private final ProductService productService;
    private final ProductPortionService productPortionService;

    public MealController(MealService mealService, UserService userService, CommentService commentService, DailyPlanService dailyPlanService, ProductService productService, ProductPortionService productPortionService) {
        this.mealService = mealService;
        this.userService = userService;
        this.commentService = commentService;
        this.dailyPlanService = dailyPlanService;
        this.productService = productService;
        this.productPortionService = productPortionService;
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

    @GetMapping("/all")
    public String allMeals(HttpServletRequest request, Model model) {
        setUserDtoAsModelAttribute(request, model);
        List<MealEntity> allMealsEntities = mealService.findAll();
        List<MealDto> allMealsDto = new ArrayList<>();

        for (MealEntity mealEntity : allMealsEntities) {
            MealDto mealDto = mealService.mapEntityToDto(mealEntity);
            if (mealEntity.getUserEntity() != null) {
                mealDto.setUserDto(userService.mapEntityToDto(userService.findById(mealEntity.getUserEntity().getId()).orElseThrow(UserNotFoundException::new)));
            }
            allMealsDto.add(mealDto);
        }
        model.addAttribute("allMeals", allMealsDto);
        return "appMealListAll";
    }

    @GetMapping("/add")
    public String addNewMeal(HttpServletRequest request, Model model) {
        setUserDtoAsModelAttribute(request, model);
        model.addAttribute("mealDto", new MealDto());
        return "appAddNewMeal";
    }

    @PostMapping(value = "/add")
    public String addMealProcessForm(@ModelAttribute("mealDto") @Valid MealDto mealDto, BindingResult result, Model model, HttpServletRequest request) {
        UserDto loggedUser = fetchUserDto(request);
        model.addAttribute("userDto", loggedUser);
        Long id = loggedUser.getId();
        MealEntity mealEntity = mealService.mapDtoToEntity(mealDto);
        mealEntity.setUserEntity(userService.findById(id).orElseThrow(UserNotFoundException::new));
        mealService.save(mealEntity);
        return "redirect:list";
    }

    @GetMapping("/details/{id}")
    public String mealDetails(HttpServletRequest request, Model model, @PathVariable Long id) {
        setUserDtoAsModelAttribute(request, model);
        MealEntity mealEntity = mealService.findById(id).orElseThrow(MealNotFoundException::new);
        MealDto mealDto = mealService.mapEntityToDto(mealEntity);
        if (mealEntity.getUserEntity() != null) {
            mealDto.setUserDto(userService.mapEntityToDto(mealEntity.getUserEntity()));
        }
        model.addAttribute("mealDto", mealDto);

        List<Long> productPortionEntitiesIds = productPortionService.findAllProductPortionsIdsByMealId(id);
        List<ProductPortionEntity> mealProductPortionEntities = new ArrayList<>();
        for (Long productPortionEntitiesId : productPortionEntitiesIds) {
            mealProductPortionEntities.add(productPortionService.findById(productPortionEntitiesId).orElseThrow(ProductNotFoundException::new));
        }

        model.addAttribute("mealProductPortions", mealProductPortionEntities);

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

    @GetMapping("/products/edit/{id}")
    public String mealPortionsEdit(HttpServletRequest request, Model model, @PathVariable Long id) {
        setUserDtoAsModelAttribute(request, model);
        MealDto mealDto = mealService.mapEntityToDto(mealService.findById(id).orElseThrow(MealNotFoundException::new));
        model.addAttribute("mealDto", mealDto);
        return "appEditMealProducts";
    }

    @PostMapping(value = "/products/edit/{id}")
    public String addMealPortionsProcessForm(@ModelAttribute("mealDto") @Valid MealDto mealDto, BindingResult result, Model model, HttpServletRequest request, @PathVariable Long id) {
        UserDto loggedUser = fetchUserDto(request);
        model.addAttribute("userDto", loggedUser);
        Long userId = loggedUser.getId();

        MealEntity mealEntity = mealService.findById(id).orElseThrow(MealNotFoundException::new);
        List<Long> productPortionEntitiesIds = productPortionService.findAllProductPortionsIdsByMealId(mealEntity.getId());

        if (mealDto.getNewProductPortionDto().getPortion() != null || mealDto.getNewProductPortionDto().getPortion() != 0) {
            ProductPortionDto newProductPortionDto = new ProductPortionDto();
            newProductPortionDto.setPortion(mealDto.getNewProductPortionDto().getPortion());

            ProductPortionEntity productPortionEntity = productPortionService.mapDtoToEntity(newProductPortionDto);
            productPortionEntity.setProductEntity(productService.findById(mealDto.getNewProductPortionDto().getProductDto().getId()).orElseThrow(ProductNotFoundException::new));
            productPortionService.save(productPortionEntity);

            productPortionEntitiesIds.add(productPortionEntity.getId());

        }
        List<ProductPortionEntity> productPortionEntities = new ArrayList<>();
        for (Long productPortionEntitiesId : productPortionEntitiesIds) {

            productPortionEntities.add(productPortionService.findById(productPortionEntitiesId).orElseThrow(ProductNotFoundException::new));
        }

        mealEntity.setProductPortions(productPortionEntities);
        mealEntity.setUserEntity(userService.findById(userId).orElseThrow(UserNotFoundException::new));

        mealEntity.setName(mealDto.getName());
        mealEntity.setDescription(mealDto.getDescription());


        double mealCalories = 0;
        double mealCarbs = 0;
        double mealFats = 0;
        double mealProtein = 0;

        for (ProductPortionEntity portionEntity : productPortionEntities) {

            mealCalories += portionEntity.getPortion() * portionEntity.getProductEntity().getCalories();
            mealCarbs += portionEntity.getPortion() * portionEntity.getProductEntity().getCarbs();
            mealFats += portionEntity.getPortion() * portionEntity.getProductEntity().getFats();
            mealProtein += portionEntity.getPortion() * portionEntity.getProductEntity().getProtein();
        }

        mealEntity.setMealCalories(mealCalories);
        mealEntity.setMealCarbs(mealCarbs);
        mealEntity.setMealFats(mealFats);
        mealEntity.setMealProtein(mealProtein);
        mealService.save(mealEntity);

        return "redirect:../../list";
    }

    private UserDto fetchUserDto(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        return userService.mapEntityToDto(userService.findByUsername(username).orElseThrow(UserNotFoundException::new));
    }

    @ModelAttribute("products")
    private List<ProductDto> fetchProductsDto() {
        return productService.mapListEntityToDto(productService.findAll());
    }

    private void setUserDtoAsModelAttribute(HttpServletRequest request, Model model) {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        model.addAttribute("userDto", userService.mapEntityToDto(userService.findByUsername(username).orElseThrow(UserNotFoundException::new)));
    }
}
