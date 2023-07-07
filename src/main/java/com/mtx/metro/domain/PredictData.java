package com.mtx.metro.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("PredictData")
public class PredictData {
    @TableField("id")
    private Integer id;

    @TableField("dateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String dateTime;

    @TableField("pEntries")
    private Integer pEntries;

    @TableField("pExits")
    private Integer pExits;
}
