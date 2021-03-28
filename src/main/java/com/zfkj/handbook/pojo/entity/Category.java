package com.zfkj.handbook.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {
    private static final long serialVersionUID = 575625603260435707L;
    @TableId(type = IdType.AUTO)
    private Integer cId;
    private String cName;
    private String cInfo;
    private String cUrl;
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private Object isDelete;

}
