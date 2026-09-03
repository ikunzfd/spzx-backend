package com.zfd.spzx.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.zfd.spzx.common.exception.SpzxException;
import com.zfd.spzx.manager.mapper.SysUserMapper;
import com.zfd.spzx.manager.service.SysUserService;
import com.zfd.spzx.model.dto.system.LoginDto;
import com.zfd.spzx.model.entity.system.SysUser;
import com.zfd.spzx.model.vo.common.ResultCodeEnum;
import com.zfd.spzx.model.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public LoginVo login(LoginDto loginDto) {

        // 校验验证码
        String codeValue = loginDto.getCaptcha();
        String key = loginDto.getCodeKey();
        String redisCode = redisTemplate.opsForValue().get("user:validate"+key);

        if (StrUtil.isEmpty(codeValue)||!StrUtil.equalsIgnoreCase(redisCode,codeValue)){
            throw new SpzxException(ResultCodeEnum.VALIDATECODE_ERROR);
        }

        redisTemplate.delete("user:validate"+key);

        String userName = loginDto.getUserName();
        SysUser sysUser = sysUserMapper.selectSysUserByUserName(userName);
        if (sysUser == null) {
            //throw new RuntimeException("用户名不存在");
            throw new SpzxException(ResultCodeEnum.LOGIN_ERROR);
        }

        String database_password = sysUser.getPassword();
        String input_password = DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes());
        if (!input_password.equals(database_password)) {
            //throw new RuntimeException("密码错误");
            throw new SpzxException(ResultCodeEnum.LOGIN_ERROR);
        }

        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue().set("user:login"+token, JSON.toJSONString(sysUser),7, TimeUnit.DAYS);

        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);

        return loginVo;
    }

    @Override
    public SysUser getUserInfo(String token) {
        String tokenJson = redisTemplate.opsForValue().get("user:login" + token);
        SysUser sysUser = JSON.parseObject(tokenJson, SysUser.class);
        return sysUser;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete("user:login" + token);
    }
}
