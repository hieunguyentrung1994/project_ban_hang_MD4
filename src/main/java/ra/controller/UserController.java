package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.model.User;
import ra.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String showUser(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("listUser", users);
        return "admin/user";
    }
    @GetMapping("/status/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.status(id);
        return "redirect:/user/list";
    }
}
