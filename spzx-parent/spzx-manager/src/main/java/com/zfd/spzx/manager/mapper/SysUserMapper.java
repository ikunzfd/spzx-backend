package com.zfd.spzx.manager.mapper;

import com.zfd.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper {
    SysUser selectSysUserByUserName(String userName);
}
