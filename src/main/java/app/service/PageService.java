package app.service;

import app.model.Page;
import app.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public final class PageService {
    @Autowired
    PageRepository pageRepository;
    @Autowired
    CategoryService categoryService;

    public Page getPage(String path) {
        var id = categoryService.getPageId(path);
        if (id == null) return null;
        return pageRepository.findById(id).orElse(null);
    }
}
