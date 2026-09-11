package com.zfd.spzx.manager.service;

import com.zfd.spzx.model.entity.product.Category;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
    List<Category> findCategoryList(Long id);

    void exportData(HttpServletResponse response);

    void importData(MultipartFile file);
}
