package com.zfd.spzx.manager.service.impl;

import com.alibaba.excel.EasyExcel;
import com.zfd.spzx.common.exception.SpzxException;
import com.zfd.spzx.manager.listener.ExcelListener;
import com.zfd.spzx.manager.mapper.CategoryMapper;
import com.zfd.spzx.manager.service.CategoryService;
import com.zfd.spzx.model.entity.product.Category;
import com.zfd.spzx.model.vo.common.ResultCodeEnum;
import com.zfd.spzx.model.vo.product.CategoryExcelVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findCategoryList(Long id) {

        List<Category> categoryList = categoryMapper.selectCategoryByParentId(id);

        if (!CollectionUtils.isEmpty(categoryList)) {
            categoryList.forEach(category -> {
                int count = categoryMapper.selectCountByParentId(category.getId());
                if (count>0){
                    category.setHasChildren(true);
                }else {
                    category.setHasChildren(false);
                }
            });
        }

        return categoryList;
    }

    @Override
    public void exportData(HttpServletResponse response) {
        try {
            // 设置响应结果类型
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");

            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("分类数据", "UTF-8");

            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            //response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");

            // 查询数据库中的数据
            List<Category> categoryList = categoryMapper.findAll();

            List<CategoryExcelVo> categoryExcelVoList = new ArrayList<>();
            for (Category category : categoryList){
                CategoryExcelVo categoryExcelVo = new CategoryExcelVo();
                BeanUtils.copyProperties(category, categoryExcelVo);
                categoryExcelVoList.add(categoryExcelVo);
            }

            EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class)
                    .sheet("分类数据").doWrite(categoryExcelVoList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SpzxException(ResultCodeEnum.DATA_ERROR);
        }
    }

    @Override
    public void importData(MultipartFile file) {

        ExcelListener<CategoryExcelVo> excelListener = new ExcelListener(categoryMapper);

        try {
            EasyExcel.read(file.getInputStream(), CategoryExcelVo.class,excelListener)
                    .sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
            throw new SpzxException(ResultCodeEnum.DATA_ERROR);
        }
    }
}
