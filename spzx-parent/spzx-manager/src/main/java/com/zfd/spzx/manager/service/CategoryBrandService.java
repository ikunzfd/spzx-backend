package com.zfd.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.zfd.spzx.model.dto.product.CategoryBrandDto;
import com.zfd.spzx.model.entity.product.CategoryBrand;

public interface CategoryBrandService {
    PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto);

    void save(CategoryBrand categoryBrand);

    void updateById(CategoryBrand categoryBrand);

    void deleteById(Long id);
}
