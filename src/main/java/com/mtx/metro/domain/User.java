package com.mtx.metro.domain;


import com.baomidou.mybatisplus.annotation.*;
import com.mtx.metro.enums.PerEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户表（User）实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("User")
public class User implements Serializable {

    private static final long serialVersionUID = -40356785423868312L;


    @TableId(type = IdType.AUTO)
    private String Id;

    @TableField
    @NotBlank(message = "用户名不能为空")
    private String Name;

    @TableField
    @NotBlank(message = "密码不能为空")
    private String Password;

    @TableField
    @NotBlank(message = "权限不能为空")
    private String Permission;

    @TableField
    @Email(message = "邮箱格式不正确")
    @NotBlank(message = "邮箱不能为空")
    private String Mail;
}
