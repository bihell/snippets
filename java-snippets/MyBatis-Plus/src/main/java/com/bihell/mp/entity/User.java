package com.bihell.mp.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author haseochen
 */
@Data
@TableName("user") //指定表名
public class User {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 姓名
     */
    @TableField("name")  // 指定数据库中列名
    private String name;
    /**
     * 年龄
     */
    @TableField(condition = SqlCondition.NOT_EQUAL)
    private Integer age;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 直属上级
     */
    private Long managerId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 备注
     */
    @TableField(exist = false)
    private transient String remark;
}
