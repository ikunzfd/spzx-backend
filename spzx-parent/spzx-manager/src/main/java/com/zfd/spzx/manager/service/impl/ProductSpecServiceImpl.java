package com.zfd.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zfd.spzx.manager.mapper.ProductSpecMapper;
import com.zfd.spzx.manager.service.ProductSpecService;
import com.zfd.spzx.model.entity.product.ProductSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSpecServiceImpl implements ProductSpecService {

    @Autowired
    private ProductSpecMapper productSpecMapper;

    @Override
    public PageInfo<ProductSpec> findByPage(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<ProductSpec> list = productSpecMapper.findByPage(page,limit);
        return new PageInfo<>(list);
    }

    @Override
    public void save(ProductSpec productSpec) {
        productSpecMapper.save(productSpec);
    }

    @Override
    public void updateById(ProductSpec productSpec) {
        productSpecMapper.updateById(productSpec);
    }

    @Override
    public void deleteById(Long id) {
        productSpecMapper.deleteById(id);
    }

    @Override
    public List<ProductSpec> findAll() {
        List<ProductSpec> list = productSpecMapper.findAll();
        return list;
    }

}
