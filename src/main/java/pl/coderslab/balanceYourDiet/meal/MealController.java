package pl.coderslab.balanceYourDiet.meal;

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
import java.util.stream.Collectors;

@Controller
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

    @PostMapping("/comment/{mealId}")
    public String addNewComment(@RequestParam String newComment, HttpServletRequest request, @PathVariable Long mealId) {
        UserDto loggedUser = fetchUserDto(request);
        Long id = loggedUser.getId();
        MealEntity mealEntity = mealService.findByIdWithComments(mealId);
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(newComment);
        commentEntity.setUserEntity(userService.findById(id).orElseThrow(UserNotFoundException::new));
        commentService.save(commentEntity);

        List<CommentEntity> commentEntities = mealEntity.getComments();
        commentEntities.add(commentEntity);
        mealEntity.setComments(commentEntities);
        mealService.save(mealEntity);
        return "redirect:list";
    }

    @PostMapping(value = "/add")
    public String addMealProcessForm(@ModelAttribute("mealDto") @Valid MealDto mealDto, BindingResult result, HttpServletRequest request) {
        UserDto loggedUser = fetchUserDto(request);
        Long id = loggedUser.getId();
        MealEntity mealEntity = mealService.mapDtoToEntity(mealDto);
        mealEntity.setUserEntity(userService.findById(id).orElseThrow(UserNotFoundException::new));
        mealService.save(mealEntity);
        return "redirect:list";
    }

    @GetMapping(value = "/copy/{mealId}")
    public String copyMealToYourMeals(HttpServletRequest request, @PathVariable Long mealId) {
        UserDto loggedUser = fetchUserDto(request);
        Long id = loggedUser.getId();
        MealEntity mealEntity = mealService.findById(mealId).orElseThrow(MealNotFoundException::new);

        MealEntity mealEntityCopy = copyMealEntity(mealEntity);
        mealEntityCopy.setUserEntity(userService.findById(id).orElseThrow(UserNotFoundException::new));
        mealService.save(mealEntityCopy);
        return "redirect:../list";
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

        List<CommentEntity> commentEntities = commentIds
                .stream()
                .map(this::getCommentEntityFromCommentId)
                .collect(Collectors.toList());

        model.addAttribute("commentsOfMeal", commentService.mapCommentListEntityToDto(commentEntities));
        return "appMealDetails";
    }

    @GetMapping("/delete/{id}")
    public String mealDelete(@PathVariable Long id) {
        List<Long> dailyPlanIdsByMealId = mealService.findAllDailyPlanIdsByMealId(id);
        if (!dailyPlanIdsByMealId.isEmpty()) {
            for (Long dailyPlanId : dailyPlanIdsByMealId) {
                DailyPlanEntity dailyPlanEntity = dailyPlanService.findById(dailyPlanId).orElseThrow(PlanNotFoundException::new);
                List<Long> mealIds = dailyPlanService.findAllMealEntitiesIdByDailyPlanId(dailyPlanEntity.getId());
                mealIds.removeIf(s -> s.equals(id));

                dailyPlanEntity.setMealEntities(mealIds
                        .stream()
                        .map(this::getMealEntityFromMealInPlanDto)
                        .collect(Collectors.toList()));

                dailyPlanService.save(dailyPlanEntity);
            }
        }

        MealEntity mealEntity = mealService.findById(id).orElseThrow(MealNotFoundException::new);
        mealEntity.setUserEntity(null);
        mealService.save(mealEntity);
        return "redirect:../list";
    }

    @GetMapping("/remove/prodPortion/{mealId}/{prodPortId}")
    public String removeProduct(@PathVariable Long prodPortId, @PathVariable Long mealId) {

        MealEntity mealEntity = mealService.findById(mealId).orElseThrow(MealNotFoundException::new);
        List<ProductPortionEntity> productPortionEntities = mealEntity.getProductPortions();

        productPortionEntities.removeIf(s -> s.getProductEntity().getId().equals(prodPortId));
        mealEntity.setProductPortions(null);
        mealService.save(mealEntity);
        mealEntity.setProductPortions(productPortionEntities);

        List<Double> nutrients = updateNutrients(productPortionEntities);
        mealEntity.setMealCalories(nutrients.get(0));
        mealEntity.setMealCarbs(nutrients.get(1));
        mealEntity.setMealFats(nutrients.get(2));
        mealEntity.setMealProtein(nutrients.get(3));
        mealService.save(mealEntity);

        return "redirect:../../../products/edit/" + mealId;
    }

    @GetMapping("/edit/prodPortion/{mealId}/{prodPortId}/{newProdPortion}")
    public String removeProduct(@PathVariable Long prodPortId, @PathVariable double newProdPortion, @PathVariable Long mealId) {

        ProductPortionEntity productPortionEntity = productPortionService.findById(prodPortId).orElseThrow(ProductPortionNotFoundException::new);
        productPortionEntity.setPortion(newProdPortion / 100);
        productPortionService.save(productPortionEntity);

        return "redirect:../../../../products/edit/" + mealId;
    }

    @GetMapping("/products/edit/{id}")
    public String mealPortionsEdit(HttpServletRequest request, Model model, @PathVariable Long id) {
        setUserDtoAsModelAttribute(request, model);
        MealDto mealDto = mealService.mapEntityToDto(mealService.findById(id).orElseThrow(MealNotFoundException::new));

        List<ProductPortionDto> productPortionDtos = productPortionService.mapListEntityToDto(productPortionService.findAllProductPortionsByMealId(id));

        for (ProductPortionDto productPortionDto : productPortionDtos) {

            ProductDto productDto = productService.mapEntityToDto(productService.findById(productPortionService.findProductEntityIdByProductPortionId(productPortionDto.getId())).orElseThrow(ProductNotFoundException::new));

            productPortionDto.setProductDto(productDto);
        }

        model.addAttribute("productPortionsInMeal", productPortionDtos);

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

        if (mealDto.getNewProductPortionDto().getPortion() != null && mealDto.getNewProductPortionDto().getPortion() != 0) {
            ProductPortionDto newProductPortionDto = new ProductPortionDto();
            newProductPortionDto.setPortion(mealDto.getNewProductPortionDto().getPortion() / 100);

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

        List<Double> nutrients = updateNutrients(productPortionEntities);
        mealEntity.setMealCalories(nutrients.get(0));
        mealEntity.setMealCarbs(nutrients.get(1));
        mealEntity.setMealFats(nutrients.get(2));
        mealEntity.setMealProtein(nutrients.get(3));
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

    private List<Double> updateNutrients(List<ProductPortionEntity> productPortionEntities) {

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

        List<Double> nutrients = new ArrayList<>();
        nutrients.add(mealCalories);
        nutrients.add(mealCarbs);
        nutrients.add(mealFats);
        nutrients.add(mealProtein);
        return nutrients;
    }

    private MealEntity copyMealEntity(MealEntity mealEntity) {

        MealEntity mealEntityCopy = new MealEntity();
        mealEntityCopy.setName(mealEntity.getName());
        mealEntityCopy.setDescription(mealEntity.getDescription());
        mealEntityCopy.setProductPortions(copyProductPortions(mealEntity.getProductPortions()));

        List<Double> nutrients = updateNutrients(mealEntity.getProductPortions());
        mealEntityCopy.setMealCalories(nutrients.get(0));
        mealEntityCopy.setMealCarbs(nutrients.get(1));
        mealEntityCopy.setMealFats(nutrients.get(2));
        mealEntityCopy.setMealProtein(nutrients.get(3));

        return mealEntityCopy;
    }

    private List<ProductPortionEntity> copyProductPortions(List<ProductPortionEntity> productPortionEntities) {

        List<ProductPortionEntity> productPortionEntitiesCopy = new ArrayList<>();

        for (ProductPortionEntity productPortionEntity : productPortionEntities) {
            ProductPortionEntity productPortionEntityCopy = new ProductPortionEntity();

            productPortionEntityCopy.setPortion(productPortionEntity.getPortion());
            productPortionEntityCopy.setProductEntity(productPortionEntity.getProductEntity());

            productPortionService.save(productPortionEntityCopy);
            productPortionEntitiesCopy.add(productPortionEntityCopy);
        }
        return productPortionEntitiesCopy;
    }

    private MealEntity getMealEntityFromMealInPlanDto(Long mealId) {
        return mealService.findById(mealId).orElseThrow(MealNotFoundException::new);
    }

    private CommentEntity getCommentEntityFromCommentId(Long commentId) {
        return commentService.findById(commentId).orElseThrow(CommentNotFoundException::new);
    }
}
