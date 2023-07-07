package com.mtx.metro.controller;

import cn.hutool.core.util.StrUtil;
import com.mtx.metro.constants.CodeConstants;
import com.mtx.metro.controller.dto.LoginDto;
import com.mtx.metro.domain.PredictData;
import com.mtx.metro.domain.Result;
import com.mtx.metro.exception.ServiceException;
import com.mtx.metro.mapper.PredictMapper;
import com.mtx.metro.mapper.UserMapper;
import com.mtx.metro.service.PredictService;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/predict")
public class PredictController {
    @Autowired
    private PredictService predictService;
//    @Autowired
//    private UserMapper userMapper;

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('1','2','3')")
//    @PreAuthorize("hasAnyAuthority('NORMAL_USER','COMPANY_USER','ADMINISTRATOR')")
    public Result selectAll(){
//        String qaq = userMapper.selectPermsByUserId(id).getValue();
//        System.out.println(qaq);
        return Result.success(predictService.selectAll());
    }
}
