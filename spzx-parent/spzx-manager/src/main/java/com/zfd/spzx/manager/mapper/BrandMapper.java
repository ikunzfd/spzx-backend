package com.zfd.spzx.manager.mapper;

import com.zfd.spzx.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BrandMapper {
    List<Brand> findByPage();

    void save(Brand brand);

    void updateById(Brand brand);

    void deleteById(Long id);

}
