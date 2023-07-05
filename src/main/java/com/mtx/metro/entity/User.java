package com.mtx.metro.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("User")
public class User {

    @TableId(value = "Id")
    private String uid;

    @TableField(value = "Name")
    private String uname;

    @TableField(value = "Password")
    private String pwd;

    @TableField(value = "Permission")
    private Permission per;
}
