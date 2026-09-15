package com.zfd.spzx.common.log.service;

import com.zfd.spzx.model.entity.system.SysOperLog;

public interface AsyncOperLogService {

    public abstract void saveSysOperLog(SysOperLog sysOperLog);

}
