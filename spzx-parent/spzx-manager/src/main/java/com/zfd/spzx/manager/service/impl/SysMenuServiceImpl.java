package com.zfd.spzx.manager.service.impl;

import com.zfd.spzx.common.exception.SpzxException;
import com.zfd.spzx.manager.mapper.SysMenuMapper;
import com.zfd.spzx.manager.mapper.SysRoleMenuMapper;
import com.zfd.spzx.manager.service.SysMenuService;
import com.zfd.spzx.manager.utils.MenuHelper;
import com.zfd.spzx.model.entity.system.SysMenu;
import com.zfd.spzx.model.entity.system.SysUser;
import com.zfd.spzx.model.vo.common.ResultCodeEnum;
import com.zfd.spzx.model.vo.system.SysMenuVo;
import com.zfd.spzx.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenu> findNodes() {

        List<SysMenu> sysMenuList = sysMenuMapper.findAll();

        if (CollectionUtils.isEmpty(sysMenuList)){
            return null;
        }

        List<SysMenu> treeList = MenuHelper.buildTree(sysMenuList);
        return treeList;
    }

    @Override
    public void save(SysMenu sysMenu) {
        sysMenuMapper.save(sysMenu);

        updateSysRoleMenu(sysMenu);
    }

    private void updateSysRoleMenu(SysMenu sysMenu) {
        SysMenu parentMenu = sysMenuMapper.selectParentMenu(sysMenu.getParentId());
        if (parentMenu != null) {
            sysRoleMenuMapper.updateSysRoleMenuIsHalf(parentMenu.getId());
            updateSysRoleMenu(parentMenu);
        }
    }

    @Override
    public void update(SysMenu sysMenu) {
        sysMenuMapper.update(sysMenu);
    }

    @Override
    public void removeById(Long id) {
         int count = sysMenuMapper.selectCountById(id);
         if (count > 0) {
             throw new SpzxException(ResultCodeEnum.NODE_ERROR);
         }

         sysMenuMapper.delete(id);
    }

    @Override
    public List<SysMenuVo> findMenusByUserId() {

        SysUser sysUser = AuthContextUtil.get();
        Long userId = sysUser.getId();

        List<SysMenu> sysMenuList = MenuHelper.buildTree(sysMenuMapper.findMenusByUserId(userId));

        List<SysMenuVo> sysMenuVos = this.buildMenus(sysMenuList);

        return sysMenuVos;
    }

    // 将List<SysMenu>对象转换成List<SysMenuVo>对象
    private List<SysMenuVo> buildMenus(List<SysMenu> menus) {

        List<SysMenuVo> sysMenuVoList = new LinkedList<SysMenuVo>();
        for (SysMenu sysMenu : menus) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setName(sysMenu.getComponent());
            List<SysMenu> children = sysMenu.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                sysMenuVo.setChildren(buildMenus(children));
            }
            sysMenuVoList.add(sysMenuVo);
        }
        return sysMenuVoList;
    }
}
