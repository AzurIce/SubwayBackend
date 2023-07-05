package com.mtx.metro.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.mtx.metro.enums.PerEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("User")
public class User {

    @TableId(type = IdType.AUTO)
    private String Id;

    @TableField
    private String Name;

    @TableField
    private String Password;

    private PerEnum Permission;

    @TableField
    private String Mail;
}
