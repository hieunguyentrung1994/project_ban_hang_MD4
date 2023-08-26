package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ra.model.Catagory;
import ra.service.CatagoryService;

import javax.servlet.http.HttpServlet;
import java.util.List;


@Controller
@RequestMapping("/category")
public class CatagoryController extends HttpServlet {

    @Autowired
    protected CatagoryService catagoryService;

    @PostMapping("/ADD")
    public String ADD(Catagory catagory) {
        catagoryService.save(new Catagory(0, catagory.getName(), true));
        return "redirect:/category/list";
    }

    @GetMapping("/list")
    public String show(Model model) {
        List<Catagory> listCategories = catagoryService.findAll();
        model.addAttribute("listCategories", listCategories);
        return "admin/category";
    }

    @GetMapping("edit/{id}")
    public ModelAndView getEdit(@PathVariable("id") long id) {
        return new ModelAndView("admin/editCatagory", "catagory", catagoryService.findById(id));
    }

    @PostMapping("/edit_Category")
    public String update(Catagory catagory) {
        catagoryService.save(new Catagory(catagory.getId(), catagory.getName(), catagory.isStatus()));
        return "redirect:/category/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        catagoryService.delete(id);
        return "redirect:/category/list";
    }


}
