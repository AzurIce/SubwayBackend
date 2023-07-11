package com.mtx.metro.controller;

import cn.hutool.core.util.StrUtil;
import com.mtx.metro.constants.CodeConstants;
import com.mtx.metro.utils.Result;
import com.mtx.metro.service.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
public class DataController {
    @Autowired
    private DataServiceImpl dataService;

    //时间范围
    @GetMapping("/time")
    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL','ROLE_COMPANY','ROLE_ADMIN')")
    public Result getTimeRange(){
        return Result.success(dataService.getTimeRange());
    }

    //全部真实数据
//    @GetMapping("/true/all")
//    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL','ROLE_COMPANY','ROLE_ADMIN')")
//    public Result selectAllTrueData(){
//        return Result.success(dataService.selectAllTrueData());
//    }

    //某时间点的某站点真实数据
    @GetMapping("/true/at")
    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL','ROLE_COMPANY','ROLE_ADMIN')")
    public Result TrueDataAtTimeStation(@RequestParam
                                            @Valid @NotBlank(message = "日期时间不能为空")
                                                    String dateTime,
                                        @RequestParam
                                            @Valid @NotBlank(message = "站名不能为空")
                                                    String GTFSid){
        return Result.success(dataService.TrueDataAtTimeStation(dateTime,GTFSid));
    }

    //某站点的最新预测数据
    @GetMapping("/predict/at")
    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL','ROLE_COMPANY','ROLE_ADMIN')")
    public Result PredictDataAtStation(@RequestParam
                                           @Valid @NotBlank(message = "站名不能为空")
                                                   String GTFSid){

        return Result.success(dataService.PredictDataAtStation(GTFSid));
    }

    //某GTFSid的站点信息
    @GetMapping("/threshold")
    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL','ROLE_COMPANY','ROLE_ADMIN')")
    public Result getThresholdInfo(@RequestParam
                                       @Valid @NotBlank(message = "站名不能为空")
                                               String GTFSid){
        return Result.success(dataService.getThresholdInfo(GTFSid));
    }

    @GetMapping("/warning")
    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL','ROLE_COMPANY','ROLE_ADMIN')")
    public Result getWarningInfo(@RequestParam
                                   @Valid @NotBlank(message = "日期时间不能为空")
                                           String dateTime){
        return Result.success(dataService.getWarningInfo(dateTime));
    }
}
