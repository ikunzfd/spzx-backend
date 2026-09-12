package com.zfd.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zfd.spzx.manager.mapper.CategoryBrandMapper;
import com.zfd.spzx.manager.service.CategoryBrandService;
import com.zfd.spzx.model.dto.product.CategoryBrandDto;
import com.zfd.spzx.model.entity.product.Brand;
import com.zfd.spzx.model.entity.product.CategoryBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryBrandServiceImpl implements CategoryBrandService {

    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    @Override
    public PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto) {
        PageHelper.startPage(page,limit);
        List<CategoryBrand> list = categoryBrandMapper.findByPage(categoryBrandDto);
        PageInfo<CategoryBrand> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void save(CategoryBrand categoryBrand) {
        categoryBrandMapper.save(categoryBrand);
    }

    @Override
    public void updateById(CategoryBrand categoryBrand) {
        categoryBrandMapper.updateById(categoryBrand);
    }

    @Override
    public void deleteById(Long id) {
        categoryBrandMapper.deleteById(id);
    }

    @Override
    public List<Brand> findBrandByCategoryId(Long categoryId) {
        List<Brand> list = categoryBrandMapper.findBrandByCategoryId(categoryId);
        return list;
    }
}
