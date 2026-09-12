package com.zfd.spzx.manager.service.impl;

import com.zfd.spzx.manager.mapper.ProductUnitMapper;
import com.zfd.spzx.manager.service.ProductUnitService;
import com.zfd.spzx.model.entity.base.ProductUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductUnitServiceImpl implements ProductUnitService {

    @Autowired
    private ProductUnitMapper productUnitMapper;

    @Override
    public List<ProductUnit> findAll() {
        List<ProductUnit> list = productUnitMapper.findAll();
        return list;
    }
}
