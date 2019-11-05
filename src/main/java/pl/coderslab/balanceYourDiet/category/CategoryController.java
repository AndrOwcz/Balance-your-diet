package pl.coderslab.balanceYourDiet.category;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.balanceYourDiet.exception.CategoryNotFoundException;
import pl.coderslab.balanceYourDiet.exception.UserNotFoundException;
import pl.coderslab.balanceYourDiet.product.ProductDto;
import pl.coderslab.balanceYourDiet.product.ProductService;
import pl.coderslab.balanceYourDiet.user.UserDto;
import pl.coderslab.balanceYourDiet.user.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@Scope("session")
@RequestMapping("/app/category")
public class CategoryController {

    private final ProductService productService;
    private final UserService userService;
    private final CategoryService categoryService;

    public CategoryController(ProductService productService, UserService userService, CategoryService categoryService) {
        this.productService = productService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @GetMapping("/product/{id}")
    public String productListFromCategory(HttpServletRequest request, Model model, @PathVariable Long id) {
        setUserDtoAsModelAttribute(request, model);
        List<ProductDto> productDtosFromCategory = productService.mapListEntityToDto(productService.findAllByCategoryId(id));
        model.addAttribute("allProdutsFromCategory", productDtosFromCategory);
        model.addAttribute("category", categoryService.mapEntityToDto(categoryService.findById(id).orElseThrow(CategoryNotFoundException::new)));
        return "appProductListFromCategory";
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
