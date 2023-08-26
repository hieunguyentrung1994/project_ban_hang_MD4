package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ra.dto.request.FormLoginDto;
import ra.dto.request.FromRegisterDto;
import ra.model.Product;
import ra.model.User;
import ra.service.CatagoryService;
import ra.service.ProductService;
import ra.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class Controllers {
    @GetMapping({"/home", "/"})
    public String index() {
        return "home";
    }
    @Autowired
    private UserService userService;
    @Autowired
    private CatagoryService catagoryService;
    @Autowired
    private ProductService productService;

 @GetMapping("/giohang")
    public String giohang(){return "giohang";}
 @GetMapping("/men")
    public String men(Model model) {
     List<Product> list = productService.getProductMen();
     model.addAttribute("ListProductMen", list);
     return "men";
 }
    @GetMapping("/news")
    public String news(Model model){
        List<Product> list = productService.getProductNew();
        model.addAttribute("ListProductMen", list);
     return "news";}

    @GetMapping("/product_detail")
    public String product_detail(){return "product_detail";}

    @GetMapping("/women")
    public String women(Model model) {
        List<Product> list = productService.getProductgril();
        model.addAttribute("ListProductWomen", list);
        return "women";
    }
    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login", "login_form", new FormLoginDto());
    }
    @GetMapping("/Register")
    public ModelAndView register() {
        return new ModelAndView("signup", "register_form", new FromRegisterDto());
    }

    @PostMapping("/Register")
    public String registers(@ModelAttribute("register_form") FromRegisterDto formRegisterDto, BindingResult errors) {
        formRegisterDto.checkValidateRegister(errors, userService);
        if (errors.hasErrors()) {
            return "signup";
        }
        userService.save(formRegisterDto);
        return "redirect:/login";
    }
    @PostMapping("/handle-login")
    public String handleLogin(HttpSession session, @ModelAttribute("login_form") FormLoginDto formLoginDto, BindingResult errors) {
        // checkk validate
        formLoginDto.checkValidate(errors,userService);
        // kiểm tra bindingresult có nhận lỗi nào không
        if(errors.hasErrors()){
            return "login";
        }
        User user = userService.login(formLoginDto);
        if(user.getIdrole().getId() == 2){
            session.setAttribute("userlogin",user);
            return "home";
        }else {
            session.setAttribute("userlogin",user);
            return "admin/index";
        }

    }


}