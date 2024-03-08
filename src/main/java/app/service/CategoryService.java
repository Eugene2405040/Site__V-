package app.service;

import app.model.Category;
import app.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {

    @Autowired
    public CategoryRepository categoryRepository;

    private Map<String, Category> categoryPathMap;
    private Map<String, Long> pageIdMap;
    private List<Category> orderedCategoryList;

    public List<Category> getOrderedCategoryList() {
        var result = new ArrayList<>(orderedCategoryList);
        result.sort(Comparator.comparing(Category::getParentId));
        return result;
    }

    public Long getPageId(String path) {
        if (pageIdMap.size() < 1) {
            refreshAll();
        }
        return pageIdMap.get(path);
    }

    private void refreshPageIdMap() {
        pageIdMap = new TreeMap<>();
        categoryPathMap.forEach((path, category) -> pageIdMap.put(path, category.getPage().getId()));
    }

    public Map<String, Category> getCategoryPathMap() {
        return categoryPathMap;
    }

    private void refreshCategoryMap() {
        categoryPathMap = new TreeMap<>();
        var categoryList = categoryRepository.findAll();
        orderedCategoryList = nestCategories(categoryList);

        orderedCategoryList.forEach(System.out::println);
        categoryList.forEach(category -> categoryPathMap.put(category.getPath(), category));
    }

    private List<Category> nestCategories(List<Category> categoryList) {
        categoryList.sort(Comparator.comparing(Category::getParentId).reversed());
        if (categoryList.get(0).getParentId() > 0) {
            if (categoryList.stream().filter(c -> c.getId() == categoryList.get(0).getParentId())
                    .toList().get(0)
                    .getChildrenSet()
                    .add(categoryList.get(0))) {
                categoryList.remove(categoryList.get(0));
            }
            nestCategories(categoryList);
        }
        return categoryList;
    }

    @PostConstruct
    public void refreshAll() {
        refreshCategoryMap();
        refreshPageIdMap();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
