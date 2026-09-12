package com.zfd.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.zfd.spzx.model.dto.product.CategoryBrandDto;
import com.zfd.spzx.model.entity.product.Brand;
import com.zfd.spzx.model.entity.product.CategoryBrand;

import java.util.List;

public interface CategoryBrandService {
    PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto);

    void save(CategoryBrand categoryBrand);

    void updateById(CategoryBrand categoryBrand);

    void deleteById(Long id);

    List<Brand> findBrandByCategoryId(Long categoryId);
}
