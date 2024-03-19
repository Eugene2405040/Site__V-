package app.controller;

import app.model.Category;
import app.model.Page;
import app.service.CategoryService;
import app.service.PageService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class PageController {

    @Autowired
    public PageService pageService;

    @Autowired
    public CategoryService categoryService;


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Главная");
        model.addAttribute("description", "description");
        Map<String, Category> categoryMap = categoryService.getCategoryPathMap();
        model.addAttribute("categories", categoryService.getOrderedCategoryList());
        return "index";
    }

    @GetMapping("/{path}")
    public ModelAndView page(
            @RequestHeader HttpHeaders httpHeaders
            , HttpSession session
            , HttpServletResponse response
            , @CookieValue(name = "JSESSIONID", required = false) String sessionId
            , @CookieValue(name = "firstVisit", required = false) String firstVisit
            , @PathVariable("path") final String path
            , Model model) {

// TODO: implement visitCounter and statistics

        System.out.println("=============  PageController  =======================");
        System.out.println("httpHeaders.entrySet() -- " + httpHeaders.entrySet());
        httpHeaders.forEach((s, stringList) -> {
            System.out.print(s);
            System.out.println(stringList);
        });

        System.out.println("sessionId -- " + sessionId);

        System.out.println("session.getId() -- " + session.getId());

        System.out.println(firstVisit);

        if (firstVisit == null || firstVisit.isBlank() || firstVisit.isEmpty()) {
            Cookie newCookie = new Cookie("firstVisit"
                    , new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date())
            );
            newCookie.setPath("/");
            response.addCookie(newCookie);
        }

        System.out.println("==================== after response.addCookie(cookieToSet); ============================");


        System.out.println("session.getId() -- " + session.getId());

        System.out.println("=============  PageController  End  =======================");

//-------------------     TODO: End.     -----------------------

        ModelAndView modelAndView = new ModelAndView();
        Map<String, Category> categoryMap = categoryService.getCategoryPathMap();

        model.addAttribute("categories"
                , categoryService.getOrderedCategoryList()
                        .stream().filter(category -> category.getParentId() == 0L)
                        .toList()
        );

        if (!categoryMap.containsKey(path)) {
            modelAndView.setStatus(HttpStatus.NOT_FOUND);
            modelAndView.setViewName("404");
            return modelAndView;
        }
        Page page = pageService.getPage(path);
        if (page == null) {
            modelAndView.setStatus(HttpStatus.NOT_FOUND);
            modelAndView.setViewName("404");
            return modelAndView;
        }
        modelAndView.setStatus(HttpStatus.OK);
        modelAndView.setViewName("index");
        model.addAttribute("title", page.getTitle());
        model.addAttribute("description", page.getDescription());
        model.addAttribute("h1", page.getH1());
        model.addAttribute("body", page.getBody());
        model.addAttribute("seo", page.getSeoBlock());

        model.addAttribute("published", categoryMap.get(path).getPublished());
        model.addAttribute("modified", categoryMap.get(path).getModified());

        model.addAttribute("path", path);
        return modelAndView;
    }
}
