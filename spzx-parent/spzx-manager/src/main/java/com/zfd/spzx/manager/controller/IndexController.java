package com.zfd.spzx.manager.controller;


import com.zfd.spzx.manager.service.SysUserService;
import com.zfd.spzx.manager.service.ValidateCodeService;
import com.zfd.spzx.model.dto.system.LoginDto;
import com.zfd.spzx.model.entity.system.SysUser;
import com.zfd.spzx.model.vo.common.Result;
import com.zfd.spzx.model.vo.common.ResultCodeEnum;
import com.zfd.spzx.model.vo.system.LoginVo;
import com.zfd.spzx.model.vo.system.ValidateCodeVo;
import com.zfd.spzx.utils.AuthContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户接口")
@RestController
@RequestMapping(value = "/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ValidateCodeService validateCodeService;

    //用户退出
    @GetMapping(value = "/logout")
    public Result logout(@RequestHeader(name = "token") String token){
        sysUserService.logout(token);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    //获取当前登录用户信息
    @GetMapping(value = "/getUserInfo")
    public Result getUserInfo(){
        return Result.build(AuthContextUtil.get(),ResultCodeEnum.SUCCESS);
    }

    //获取当前登录用户信息
//    @GetMapping(value = "/getUserInfo")
//    public Result getUserInfo(@RequestHeader(name = "token") String token){
//        SysUser sysUser = sysUserService.getUserInfo(token);
//        return Result.build(sysUser,ResultCodeEnum.SUCCESS);
//    }

    //生成验证码
    @GetMapping(value = "/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode() {
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo , ResultCodeEnum.SUCCESS) ;
    }

    //用户登录
    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto) {
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }
}
