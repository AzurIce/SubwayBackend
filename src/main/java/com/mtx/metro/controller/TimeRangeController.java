package com.mtx.metro.controller;

import com.mtx.metro.utils.Result;
import com.mtx.metro.service.impl.TrueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableTransactionManagement
public class TimeRangeController {

    @Autowired
    private TrueServiceImpl trueService;

    @GetMapping("/time")
    @Transactional(rollbackFor = Exception.class)
    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL','ROLE_COMPANY','ROLE_ADMIN')")
    public Result getTimeRange(){
        return Result.success(trueService.getTimeRange());
    }
}
