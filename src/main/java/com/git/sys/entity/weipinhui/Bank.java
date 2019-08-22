package com.git.sys.entity.weipinhui;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author authorZhao
 * @since 2019-08-22
 */
@Data
@Accessors(chain = true)
@TableName("bank")
public class Bank implements Serializable {

    private static final long serialVersionUID = 1L;

   @TableId(value="id", type= IdType.AUTO)
   private Long id;
    /**
     * 银行名称
     */
   private String bankName;
    /**
     * 银行码
     */
   @TableField("bank_code")
   private String bankCode;
    /**
     * 父id
     */
   private Long pid;


}
