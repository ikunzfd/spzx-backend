package com.zfd.spzx.manager.service;

import com.zfd.spzx.model.dto.system.LoginDto;
import com.zfd.spzx.model.entity.system.SysUser;
import com.zfd.spzx.model.vo.system.LoginVo;

public interface SysUserService {
    LoginVo login(LoginDto loginDto);

    SysUser getUserInfo(String token);

    void logout(String token);
}
