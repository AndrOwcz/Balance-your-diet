package pl.coderslab.balanceYourDiet.product;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.balanceYourDiet.category.CategoryDto;
import pl.coderslab.balanceYourDiet.category.CategoryEntity;
import pl.coderslab.balanceYourDiet.category.CategoryService;
import pl.coderslab.balanceYourDiet.exception.CategoryNotFoundException;
import pl.coderslab.balanceYourDiet.exception.UserNotFoundException;
import pl.coderslab.balanceYourDiet.user.UserDto;
import pl.coderslab.balanceYourDiet.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
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

    @GetMapping("/add")
    public String addProduct(HttpServletRequest request, Model model) {
        setUserDtoAsModelAttribute(request, model);
        model.addAttribute("productDto", new ProductDto());
        return "appAddNewProduct";
    }

    @PostMapping(value = "/add")
    public String addProductProcessForm(@ModelAttribute("productDto") @Valid ProductDto productDto, BindingResult result, Model model, HttpServletRequest request) {
        setUserDtoAsModelAttribute(request, model);
        ProductEntity productEntity = productService.mapDtoToEntity(productDto);

        CategoryEntity categoryEntity = categoryService.findById(productDto.getCategoryDto().getId())
                .orElseThrow(CategoryNotFoundException::new);

        productEntity.setCategoryEntity(categoryEntity);
        productService.save(productEntity);
        return "redirect:list";
    }

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
