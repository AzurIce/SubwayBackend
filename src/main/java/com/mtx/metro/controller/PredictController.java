package com.mtx.metro.controller;

import com.mtx.metro.utils.Result;
import com.mtx.metro.service.PredictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableTransactionManagement
@RequestMapping("/predict")
public class PredictController {
    @Autowired
    private PredictService predictService;

    @GetMapping("/all")
    @Transactional(rollbackFor = Exception.class)
    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL','ROLE_COMPANY','ROLE_ADMIN')")
    public Result selectAll(){
        return Result.success(predictService.selectAll());
    }

}
