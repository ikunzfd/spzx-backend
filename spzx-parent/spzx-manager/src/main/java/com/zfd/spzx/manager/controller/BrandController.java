package com.zfd.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.zfd.spzx.common.log.annotation.Log;
import com.zfd.spzx.common.log.enums.OperatorType;
import com.zfd.spzx.manager.service.BrandService;
import com.zfd.spzx.model.entity.product.Brand;
import com.zfd.spzx.model.vo.common.Result;
import com.zfd.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/product/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Log(title = "品牌管理：列表",businessType = 0,operatorType = OperatorType.OTHER)
    @GetMapping("/{page}/{limit}")
    public Result findByPage(@PathVariable Integer page, @PathVariable Integer limit) {
        PageInfo<Brand> list = brandService.findByPage(page, limit);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Brand brand) {
        brandService.save(brand);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @PutMapping("updateById")
    public Result updateById(@RequestBody Brand brand) {
        brandService.updateById(brand);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Long id) {
        brandService.deleteById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/findAll")
    public Result findAll() {
        List<Brand> list = brandService.findAll();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}
