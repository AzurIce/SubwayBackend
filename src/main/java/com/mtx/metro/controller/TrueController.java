package com.mtx.metro.controller;

import com.mtx.metro.utils.Result;
import com.mtx.metro.service.impl.TrueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableTransactionManagement
@RequestMapping("/true")
public class TrueController {
    @Autowired
    private TrueServiceImpl trueService;

    @GetMapping("/all")
    @Transactional(rollbackFor = Exception.class)
    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL','ROLE_COMPANY','ROLE_ADMIN')")
    public Result selectAll(){
        return Result.success(trueService.selectAll());
    }

    @GetMapping("/at")
    @Transactional(rollbackFor = Exception.class)
    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL','ROLE_COMPANY','ROLE_ADMIN')")
    public Result selectAtATime(@RequestParam String dateTime){
        return Result.success(trueService.selectAtATime(dateTime));
    }


}
///0 4 8 12 16 20
