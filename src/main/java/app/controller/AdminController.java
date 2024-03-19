package app.controller;

import app.model.Category;
import app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class AdminController {


    @Autowired
    CategoryService categoryService;

    @GetMapping("/admin")
    public String newPage(Model model){
        model.addAttribute("title", "New Page");
        model.addAttribute("description", "New Page");
        model.addAttribute("t", "Admin");
        return "admin/index";
    }

    @GetMapping("/admin/delete")
    public String category(Model model){
        model.addAttribute("title", "Removing Page");
        model.addAttribute("description", "Removing Page");
        Map<String, Category> categoryMap =  categoryService.getCategoryPathMap();
        model.addAttribute("categories", categoryMap.values());
        model.addAttribute("scripts", "/js/admin/delete.js");
        return "admin/categories";
    }

    @GetMapping("/admin/create")
    public String adminCreate(Model model){
        model.addAttribute("title", "New Page");
        model.addAttribute("description", "New Page");
        model.addAttribute("categories", categoryService.getCategoryPathMap().values());

        List<String> scriptSrcList = new ArrayList<>();
        scriptSrcList.add("/js/admin/formFieldsToJSON.js");
        scriptSrcList.add("/js/admin/sendFormData.js");
        scriptSrcList.add("/js/admin/translator.js");
        model.addAttribute("scripts", scriptSrcList);
        return "admin/create";
    }

    @GetMapping("/admin/update")
    public String update(Model model){
        model.addAttribute("title", "Update Page");
        model.addAttribute("description", "Update Page");
        model.addAttribute("categories", categoryService.getCategoryPathMap().values());
        model.addAttribute("scripts", "/js/admin/update.js");
        return "admin/categories";
    }

    @GetMapping("/admin/update/{id}")
    public String update(@PathVariable(name = "id") Long id, Model model){
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            return "redirect:/admin/update";
        }
        model.addAttribute("title", "Update Page" + id);
        model.addAttribute("description", "Update Page" + id);
        model.addAttribute("category", category);
        model.addAttribute("categories", categoryService.getCategoryPathMap().values());
        model.addAttribute("scripts"
                , new String[]{"/js/admin/formFieldsToJSON.js", "/js/admin/updateSendJSON.js"});
       return "admin/create";
    }


}
