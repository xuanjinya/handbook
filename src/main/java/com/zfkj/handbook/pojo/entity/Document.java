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
public class Document implements Serializable {
    private static final long serialVersionUID = -66914718687705231L;
    @TableId(type = IdType.AUTO)
    private Integer dId;
    private Integer cId;
    private String catalog;
    private String dName;
    private String createMan;
    private Integer dStatus;
    private String dType;
    private Object dContent;
    private String dUrl;
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private Object isDelete;
}
