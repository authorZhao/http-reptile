package com.git.sys.entity.weipinhui;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 区域表
 */
@Data
@Accessors(chain = true)
@TableName("area")
public class Area {


    /**
     * 省市id
     */
    @TableId(value="area_code", type= IdType.AUTO)
    private Long areaCode;

    /**
     * 区域类型
     */
    @TableField("area_type")
    private Integer areaType;

    /**
     * 区域名称
     */
    @TableField("area_name")
    private String areaName;

    /**
     * 父id
     */
    @TableField("parent_code")
    private Long parentCode;
}
