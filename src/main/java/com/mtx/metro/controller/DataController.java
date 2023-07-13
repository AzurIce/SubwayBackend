package com.mtx.metro.controller;

import com.mtx.metro.domain.Result;
import com.mtx.metro.service.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //查询所有站点名称
    @GetMapping("/station/name")
    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL','ROLE_COMPANY','ROLE_ADMIN')")
    public ResponseEntity<Result> getAllStationName(){
        return new ResponseEntity<>(Result.success(dataService.getAllStationName()), HttpStatus.OK);
    }

    //时间范围
    @GetMapping("/time")
    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL','ROLE_COMPANY','ROLE_ADMIN')")
    public ResponseEntity<Result> getTimeRange(){
        return new ResponseEntity<>(Result.success(dataService.getTimeRange()), HttpStatus.OK);
    }

    //全部真实数据
    @GetMapping("/true/all")
    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL','ROLE_COMPANY','ROLE_ADMIN')")
    public ResponseEntity<Result> TrueDataAtTime(@RequestParam
                                        @Valid @NotBlank(message = "日期时间不能为空")
                                                String dateTime){
        return new ResponseEntity<>(Result.success(dataService.TrueDataAtTime(dateTime)), HttpStatus.OK);
    }

    //某时间点的某站点真实数据
    @GetMapping("/true/at")
    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL','ROLE_COMPANY','ROLE_ADMIN')")
    public ResponseEntity<Result> TrueDataAtTimeStation(@RequestParam
                                            @Valid @NotBlank(message = "日期时间不能为空")
                                                    String dateTime,
                                        @RequestParam
                                            @Valid @NotBlank(message = "站名不能为空")
                                                    String GTFSid){
        return new ResponseEntity<>(Result.success(dataService.TrueDataAtTimeStation(dateTime,GTFSid)), HttpStatus.OK);
    }

    //某站点的最新预测数据
//    @GetMapping("/predict/at")
//    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL','ROLE_COMPANY','ROLE_ADMIN')")
//    public ResponseEntity<Result> PredictDataAtStation(@RequestParam
//                                           @Valid @NotBlank(message = "站名不能为空")
//                                                   String GTFSid){
//
//        return new ResponseEntity<>(Result.success(dataService.PredictDataAtStation(GTFSid)), HttpStatus.OK);
//    }

    //某GTFSid的站点信息
    @GetMapping("/threshold")
    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL','ROLE_COMPANY','ROLE_ADMIN')")
    public ResponseEntity<Result> getThresholdInfo(@RequestParam
                                       @Valid @NotBlank(message = "站名不能为空")
                                               String GTFSid){
        return new ResponseEntity<>(Result.success(dataService.getThresholdInfo(GTFSid)), HttpStatus.OK);
    }

    //查询某日期时间的流量预警信息
    @GetMapping("/warning")
    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL','ROLE_COMPANY','ROLE_ADMIN')")
    public ResponseEntity<Result> getWarningInfo(@RequestParam
                                   @Valid @NotBlank(message = "日期时间不能为空")
                                           String dateTime){
        return new ResponseEntity<>(Result.success(dataService.getWarningInfo(dateTime)), HttpStatus.OK);
    }

    //查询某时间所有过载站点
//    @GetMapping("/overload")
//    @PreAuthorize("hasAnyAuthority('ROLE_NORMAL','ROLE_COMPANY','ROLE_ADMIN')")
//    public ResponseEntity<Result> getOverloadInfo(@RequestParam
//                                                 @Valid @NotBlank(message = "日期时间不能为空")
//                                                         String dateTime){
//        return new ResponseEntity<>(Result.success(dataService.getOverloadInfo(dateTime)), HttpStatus.OK);
//    }
}
