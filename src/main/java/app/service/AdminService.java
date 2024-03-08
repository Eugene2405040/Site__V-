package app.service;

import app.model.Category;
import app.repository.CategoryRepository;
import app.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class AdminService {
    @Autowired
    PageRepository pageRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;

    public ResponseEntity<?> createPage(Category category) {
        String result = "";
        if (categoryService.getPageId(category.getPath()) != null ||
                category.getPath().length() < 1) {
            result += "Choose another 'URL' \n";
        }
        if (categoryService.getCategoryPathMap().values().stream()
                .filter(c -> c.getName().equals(category.getName())).toList().size() > 0) {
            result += "Choose another 'Category name' \n";
        }

        if (pageRepository.existsByTitle(category.getPage().getTitle())) {
            result += "Choose another 'Title' \n";
        }
        if (pageRepository.existsByDescription(category.getPage().getDescription())) {
            result += "Choose another 'Description' \n";
        }

        if (result.length() > 0) return ResponseEntity.badRequest().body(result);
        categoryRepository.save(category);
        categoryService.refreshAll();
        return ResponseEntity.ok("Page created. Page id = " + category.getPage().getId()
                + "\nCategory id = " + category.getId()
                + "\nCategory name = " + category.getName());
    }

    public Long deletePage(Long id) {
        categoryRepository.deleteById(id);
        categoryService.refreshAll();
        return id;
    }

    public Category update(Long id, Category category) {
        var presentCategory = categoryRepository.findById(id).orElse(null);
        assert presentCategory != null;
        if (category.getModified() != null) {
            presentCategory.setModified(category.getModified());
        }
        presentCategory.setSequence(category.getSequence());
        presentCategory.setParentId(category.getParentId());
        presentCategory.setName(category.getName());
        presentCategory.setPath(category.getPath());

        presentCategory.getPage().setH1(category.getPage().getH1());
        presentCategory.getPage().setTitle(category.getPage().getTitle());
        presentCategory.getPage().setDescription(category.getPage().getDescription());
        presentCategory.getPage().setKeyWords(category.getPage().getKeyWords());
        presentCategory.getPage().setBody(category.getPage().getBody());
        presentCategory.getPage().setSeoBlock(category.getPage().getSeoBlock());

        category = categoryRepository.save(presentCategory);

        categoryService.refreshAll();
        System.out.println(category);
        System.out.println(category.getPage());
        return category;
    }
}
