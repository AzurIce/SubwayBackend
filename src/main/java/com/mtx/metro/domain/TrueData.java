package com.mtx.metro.domain;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("TrueData")
public class TrueData {
    @TableField("id")
    private Integer id;

    @TableField("dateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String dateTime;

    @TableField("tEntries")
    private Integer tEntries;

    @TableField("tExits")
    private Integer tExits;
}
