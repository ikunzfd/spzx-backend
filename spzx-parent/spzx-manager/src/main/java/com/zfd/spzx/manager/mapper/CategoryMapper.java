package com.zfd.spzx.manager.mapper;

import com.zfd.spzx.model.entity.product.Category;
import com.zfd.spzx.model.vo.product.CategoryExcelVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> selectCategoryByParentId(Long id);

    int selectCountByParentId(Long id);

    List<Category> findAll();

    void batchInsert(List<CategoryExcelVo> categoryList);

}
