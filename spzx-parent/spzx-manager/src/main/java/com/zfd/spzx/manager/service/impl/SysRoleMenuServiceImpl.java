package com.zfd.spzx.manager.service.impl;

import com.zfd.spzx.manager.mapper.SysMenuMapper;
import com.zfd.spzx.manager.mapper.SysRoleMenuMapper;
import com.zfd.spzx.manager.service.SysMenuService;
import com.zfd.spzx.manager.service.SysRoleMenuService;
import com.zfd.spzx.model.dto.system.AssginMenuDto;
import com.zfd.spzx.model.entity.system.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper ;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public Map<String, Object> findSysRoleMenuByRoleId(Long roleId) {

        List<SysMenu> sysMenuList = sysMenuService.findNodes() ;

        List<Long> roleMenuIds = sysRoleMenuMapper.findSysRoleMenuByRoleId(roleId);

        HashMap<String, Object> map = new HashMap<>();
        map.put("sysMenuList", sysMenuList);
        map.put("roleMenuIds", roleMenuIds);

        return map;
    }

    @Override
    public void doAssign(AssginMenuDto assginMenuDto) {

        sysRoleMenuMapper.deleteByRoleId(assginMenuDto.getRoleId());

        List<Map<String, Number>> menuInfo = assginMenuDto.getMenuIdList();
        if (menuInfo!=null && menuInfo.size()>0) {
            sysRoleMenuMapper.doAssign(assginMenuDto);
        }
    }
}
