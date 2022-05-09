package com.epro.springboot.quartz.job.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.epro.springboot.quartz.job.domain.SysJobLog;
import com.epro.springboot.quartz.job.mapper.SysJobLogMapper;
import org.springframework.stereotype.Service;

@Service
public class SysJobLogService extends ServiceImpl<SysJobLogMapper, SysJobLog> {

}
