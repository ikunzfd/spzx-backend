package com.zfd.spzx.manager.service.impl;

import com.zfd.spzx.common.log.service.AsyncOperLogService;
import com.zfd.spzx.manager.mapper.SysOperLogMapper;
import com.zfd.spzx.model.entity.system.SysOperLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsyncOperLogServiceImpl implements AsyncOperLogService {

    @Autowired
    private SysOperLogMapper sysOperLogMapper;

    @Override
    public void saveSysOperLog(SysOperLog sysOperLog) {
        sysOperLogMapper.insert(sysOperLog);
    }

}
