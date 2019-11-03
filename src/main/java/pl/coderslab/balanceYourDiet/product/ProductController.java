package pl.coderslab.balanceYourDiet.product;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.balanceYourDiet.category.CategoryDto;
import pl.coderslab.balanceYourDiet.category.CategoryService;
import pl.coderslab.balanceYourDiet.exception.UserNotFoundException;
import pl.coderslab.balanceYourDiet.user.UserDto;
import pl.coderslab.balanceYourDiet.user.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@Scope("session")
@RequestMapping("/app/product")
public class ProductController {

    private final ProductService productService;
    private final UserService userService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, UserService userService, CategoryService categoryService) {
        this.productService = productService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public String productList(HttpServletRequest request, Model model) {
        setUserDtoAsModelAttribute(request, model);
        List<ProductEntity> productEntities = productService.findAllWithCategories();
        model.addAttribute("allProduts", productService.mapListEntityToDtoWithCategories(productEntities));
        return "appProductList";
    }


//    @GetMapping("/add")
//    public String dashboard(HttpServletRequest request, Model model) {
//        setUserDtoAsModelAttribute(request, model);
//        return "appAddNewMeal";
//    }
//
//    //todo postmapping
//
//    @GetMapping("/details/{id}")
//    public String mealDetails(HttpServletRequest request, Model model, @PathVariable Long id) {
//        setUserDtoAsModelAttribute(request, model);
//        model.addAttribute("mealDto", mealService.mapEntityToDto(mealService.findById(id).orElseThrow(MealNotFoundException::new)));
//
//        List<Long> commentIds = mealService.findAllCommentEntitiesIdByMealId(id);
//        List<CommentEntity> commentsToAdd = new ArrayList<>();
//
//        for (Long commentId : commentIds) {
//            CommentEntity commentEntity = commentService.findById(commentId).orElseThrow(CommentNotFoundException::new);
//            commentsToAdd.add(commentEntity);
//        }
//        model.addAttribute("commentsOfMeal", commentService.mapCommentListEntityToDto(commentsToAdd));
//        return "appMealDetails";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String mealDelete(HttpServletRequest request, Model model, @PathVariable Long id) {
//        setUserDtoAsModelAttribute(request, model);
//
//        List<Long> dailyPlanIdsByMealId = mealService.findAllDailyPlanIdsByMealId(id);
//        if (!dailyPlanIdsByMealId.isEmpty()) {
//            for (Long dailyPlanId : dailyPlanIdsByMealId) {
//                DailyPlanEntity dailyPlanEntity = dailyPlanService.findById(dailyPlanId).orElseThrow(PlanNotFoundException::new);
//                List<Long> mealIds = dailyPlanService.findAllMealEntitiesIdByDailyPlanId(dailyPlanEntity.getId());
//                //todo
////                dailyPlanEntity.setMealEntities();
//                model.addAttribute("isAssigned", true);
//            }
//        } else {
//            mealService.deleteById(id);
//        }
//        return "redirect:../list";
//    }

    @ModelAttribute("categories")
    private List<CategoryDto> fetchCategoresDto() {
        return categoryService.mapCategoryListEntityToDto(categoryService.findAll());
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
