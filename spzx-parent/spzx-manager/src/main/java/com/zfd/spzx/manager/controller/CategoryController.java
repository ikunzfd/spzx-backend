package com.zfd.spzx.manager.controller;

import com.zfd.spzx.manager.service.CategoryService;
import com.zfd.spzx.model.entity.product.Category;
import com.zfd.spzx.model.vo.common.Result;
import com.zfd.spzx.model.vo.common.ResultCodeEnum;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/product/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("exportData")
    public void exportData(HttpServletResponse response) {
        categoryService.exportData(response);
    }

    @PostMapping("importData")
    public Result importData(MultipartFile file) {
        categoryService.importData(file);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("findCategoryList/{id}")
    public Result findCategoryList(@PathVariable Long id) {
        List<Category> list = categoryService.findCategoryList(id);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

}
