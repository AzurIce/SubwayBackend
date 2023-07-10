package com.mtx.metro.domain;


import com.baomidou.mybatisplus.annotation.*;
import com.mtx.metro.enums.PerEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
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

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long Id;
    /**
     * 用户名
     */
    @TableField
    private String Name;
    /**
     * 密码
     */
    @TableField
    private String Password;
    /**
     * 用户权限
     */
    private String Permission;
    /**
     * 邮箱
     */
    @TableField
    @Email(message = "邮箱格式不正确")
    private String Mail;
}
