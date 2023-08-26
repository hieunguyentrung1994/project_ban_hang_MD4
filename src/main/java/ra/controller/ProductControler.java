package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ra.dto.request.FormProductDto;
import ra.model.Catagory;
import ra.model.Product;
import ra.service.CatagoryService;
import ra.service.ProductService;

import java.io.File;
import java.io.IOException;


@Controller
@RequestMapping("/product")
@PropertySource("classpath:upload.properties")
public class ProductControler {


    @Autowired
    private ProductService productService;
    @Autowired
    private CatagoryService catagoryService;


    @Value("${upload-path}")
    private String uploadPath;


    @GetMapping("/list")
    public ModelAndView show(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("catagorys", catagoryService.findAll());
        return new ModelAndView("admin/product");
    }


    @PostMapping("/ADD")
    public String addProduct(@ModelAttribute("product") FormProductDto product,@RequestParam ("catagory") Long id ) {
        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdir();
        }
        String img = product.getImg().getOriginalFilename();
        try {
            FileCopyUtils.copy(product.getImg().getBytes(), new File(uploadPath + img));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Catagory cat = catagoryService.findById(id);
        Product p = new Product();
        p.setId(0);
        p.setImg(img);
        p.setIdCategory(cat);
        p.setNameProduct(product.getNameProduct());
        p.setContent(product.getContent());
        p.setQuantityInStock(product.getQuantityInStock());
        p.setPrice(product.getPrice());
        p.setSex(product.isSex());
        p.setStatus(product.isStatus());
        productService.save(p);
        return "redirect:/product/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        productService.delete(id);
        return "redirect:/product/list";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView getEdit(@PathVariable("id") long id,Model model) {
        model.addAttribute("catagorys", catagoryService.findAll());
        return new ModelAndView("admin/editProduct", "product", productService.findById(id));
    }
    @PostMapping("/edit_Product")
    public String update(@ModelAttribute("product") FormProductDto product, @RequestParam("catagory") Long id){
        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdir();
        }
        String img = product.getImg().getOriginalFilename();
        try {
            FileCopyUtils.copy(product.getImg().getBytes(), new File(uploadPath + img));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Catagory cat = catagoryService.findById(id);
        Product p = new Product();
        p.setId(product.getId());
        p.setImg(img);
        p.setIdCategory(cat);
        p.setNameProduct(product.getNameProduct());
        p.setContent(product.getContent());
        p.setQuantityInStock(product.getQuantityInStock());
        p.setPrice(product.getPrice());
        p.setSex(product.isSex());
        p.setStatus(product.isStatus());
        productService.save(p);
        return "redirect:/product/list";
    }



}
