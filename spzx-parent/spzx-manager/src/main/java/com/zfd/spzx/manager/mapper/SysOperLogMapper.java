package com.zfd.spzx.manager.mapper;

import com.zfd.spzx.model.entity.system.SysOperLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysOperLogMapper {
    void insert(SysOperLog sysOperLog);
}
