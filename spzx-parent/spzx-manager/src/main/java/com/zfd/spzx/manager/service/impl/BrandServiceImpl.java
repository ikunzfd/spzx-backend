package com.zfd.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zfd.spzx.manager.mapper.BrandMapper;
import com.zfd.spzx.manager.service.BrandService;
import com.zfd.spzx.model.entity.product.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public PageInfo<Brand> findByPage(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Brand> brandList = brandMapper.findByPage();
        PageInfo<Brand> pageInfo = new PageInfo<>(brandList);
        return pageInfo;
    }

    @Override
    public void save(Brand brand) {
        brandMapper.save(brand);
    }

    @Override
    public void updateById(Brand brand) {
        brandMapper.updateById(brand);
    }

    @Override
    public void deleteById(Long id) {
        brandMapper.deleteById(id);
    }

    @Override
    public List<Brand> findAll() {
        return brandMapper.findByPage();
    }
}
