package com.zfd.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.zfd.spzx.manager.service.SysRoleService;
import com.zfd.spzx.model.dto.system.SysRoleDto;
import com.zfd.spzx.model.entity.system.SysRole;
import com.zfd.spzx.model.vo.common.Result;
import com.zfd.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService ;

    @DeleteMapping(value = "/deleteById/{roleId}")
    public Result deleteSysRole(@PathVariable(value = "roleId") Long roleId) {
        sysRoleService.deleteById(roleId);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @PutMapping(value = "/updateSysRole")
    public Result updateSysRole(@RequestBody SysRole SysRole) {
        sysRoleService.updateSysRole(SysRole);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @PostMapping(value = "/saveSysRole")
    public Result saveSysRole(@RequestBody SysRole SysRole) {
        sysRoleService.saveSysRole(SysRole);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @PostMapping("/findByPage/{pageNum}/{pageSize}")
    public Result findByPage(@RequestBody SysRoleDto sysRoleDto ,
                             @PathVariable(value = "pageNum") Integer pageNum ,
                             @PathVariable(value = "pageSize") Integer pageSize) {
        PageInfo<SysRole> pageInfo = sysRoleService.findByPage(sysRoleDto , pageNum , pageSize) ;
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }

}

