package com.zfd.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.zfd.spzx.model.dto.system.SysRoleDto;
import com.zfd.spzx.model.entity.system.SysRole;

public interface SysRoleService {
    PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer pageNum, Integer pageSize);

    void saveSysRole(SysRole sysRole);

    void updateSysRole(SysRole sysRole);

    void deleteById(Long roleId);
}
