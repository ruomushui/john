package com.epro.springboot.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@TableName(value = "User")//默认将类名作为表名
public class User {

    /**
     * 参数一:对应数据库中主键的名称
     * 参数二：id自增
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    //如果数据库里的名字不叫name，叫username,则使用这个标签
    @TableField
    private String name;
    private Integer age;
//    private Date bir;

    /**
     * 不映射数据库表中任何字段
     */
    @TableField(exist = false)
    private String aaa;
}

