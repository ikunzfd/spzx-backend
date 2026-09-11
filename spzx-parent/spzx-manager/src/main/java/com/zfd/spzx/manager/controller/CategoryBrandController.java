package com.zfd.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.zfd.spzx.manager.service.CategoryBrandService;
import com.zfd.spzx.model.dto.product.CategoryBrandDto;
import com.zfd.spzx.model.entity.product.CategoryBrand;
import com.zfd.spzx.model.vo.common.Result;
import com.zfd.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/product/categoryBrand")
public class CategoryBrandController {

    @Autowired
    private CategoryBrandService categoryBrandService;

    @GetMapping("/{page}/{limit}")
    public Result findByPage(@PathVariable Integer page,
                             @PathVariable Integer limit,
                             CategoryBrandDto categoryBrandDto) {
        PageInfo<CategoryBrand> pageInfo = categoryBrandService.findByPage(page, limit, categoryBrandDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/save")
    public Result save(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.save(categoryBrand);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/updateById")
    public Result updateById(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.updateById(categoryBrand);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Long id) {
        categoryBrandService.deleteById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
}
