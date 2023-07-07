package com.mtx.metro.controller;

import com.mtx.metro.domain.Result;
import com.mtx.metro.service.impl.TrueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/true")
public class TrueController {
    @Autowired
    private TrueServiceImpl trueService;

    @GetMapping("/all")
    public Result selectAll(){
        return Result.success(trueService.selectAll());
    }

    @GetMapping("/at")
    public Result selectAtATime(@RequestParam String dateTime){
        return Result.success(trueService.selectAtATime(dateTime));
    }
}
