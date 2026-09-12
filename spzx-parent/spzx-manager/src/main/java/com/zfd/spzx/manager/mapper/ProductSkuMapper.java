package com.zfd.spzx.manager.mapper;

import com.zfd.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductSkuMapper {
    void save(ProductSku productSku);

    List<ProductSku> findProductSkuByProductId(Long id);

    void updateById(ProductSku productSku);

    void deleteByProductId(Long id);
}
