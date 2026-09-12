package com.zfd.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.zfd.spzx.model.dto.product.ProductDto;
import com.zfd.spzx.model.entity.product.Product;

public interface ProductService {
    PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto);

    void save(Product product);

    Product getById(Long id);

    void update(Product product);

    void deleteById(Long id);

    void updateAuditStatus(Long id, Integer auditStatus);

    void updateStatus(Long id, Integer status);
}
