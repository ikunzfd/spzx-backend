package com.zfd.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zfd.spzx.manager.mapper.SysRoleMapper;
import com.zfd.spzx.manager.mapper.SysRoleUserMapper;
import com.zfd.spzx.manager.service.SysRoleService;
import com.zfd.spzx.model.dto.system.SysRoleDto;
import com.zfd.spzx.model.entity.system.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Override
    public PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysRole> list = sysRoleMapper.findByPage(sysRoleDto);
        PageInfo<SysRole> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void saveSysRole(SysRole sysRole) {
        sysRoleMapper.save(sysRole);
    }

    @Override
    public void updateSysRole(SysRole sysRole) {
        sysRoleMapper.update(sysRole);
    }

    @Override
    public void deleteById(Long roleId) {
        sysRoleMapper.delete(roleId);
    }

    @Override
    public Map<String, Object> findAll(Long userId) {
        List<SysRole> roleList = sysRoleMapper.findAll();

        List<Long> roleIds = sysRoleUserMapper.selectRoleIdsByUserId(userId);

        HashMap<String, Object> map = new HashMap<>();
        map.put("allRolesList",roleList);
        map.put("sysUserRoles",roleIds);

        return map;
    }
}
