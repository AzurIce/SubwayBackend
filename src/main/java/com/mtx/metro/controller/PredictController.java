package com.mtx.metro.controller;

import cn.hutool.core.util.StrUtil;
import com.mtx.metro.constants.CodeConstants;
import com.mtx.metro.controller.dto.LoginDto;
import com.mtx.metro.domain.PredictData;
import com.mtx.metro.domain.Result;
import com.mtx.metro.exception.ServiceException;
import com.mtx.metro.service.PredictService;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/predict")
public class PredictController {
    private PredictService predictService;

    @GetMapping("/selectall")
    public Result selectAll(){
        return Result.success(predictService.selectAll());
    }
}
