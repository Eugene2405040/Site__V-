package app.controller;

import app.model.Category;
import app.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminRestController {

    @Autowired
    private AdminService adminService;


    @PostMapping("/admin/create")
    public ResponseEntity<?> create(@RequestBody() @Validated Category category
//            , @AuthenticationPrincipal UserDetails userDetails
    ) {
        return adminService.createPage(category);
    }

    @DeleteMapping("/admin/delete/{id}")
    public String delete(@PathVariable Long id) {
        if (id < 1L) return null;
        return "Page " + adminService.deletePage(id) + "removed";
    }

    @PutMapping("admin/update/{id}")
    public Long update(@PathVariable Long id, @RequestBody()
            Category category
//            , @AuthenticationPrincipal UserDetails userDetails
    ) {
        return adminService.update(id, category).getId();
    }

    @GetMapping("/log-out")
    public void logout(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            request.getSession().invalidate();
        }
    }
}
