package com.zfd.spzx.manager.mapper;

import com.zfd.spzx.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDetailsMapper {
    void save(ProductDetails productDetails);

    ProductDetails findProductDetailsById(Long id);

    void updateById(ProductDetails productDetails);

    void deleteByProductId(Long id);
}
