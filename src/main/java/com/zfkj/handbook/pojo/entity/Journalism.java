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
public class Journalism implements Serializable {
    private static final long serialVersionUID = -32108993841375124L;

    @TableId(type = IdType.AUTO)
    private Integer jId;
    private String jTitle;
    private String jUrl;
    private String jContent;
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private Object isDelete;

    public Journalism(Integer jId, String jTitle, String jUrl, String jContent) {
        this.jId = jId;
        this.jTitle = jTitle;
        this.jUrl = jUrl;
        this.jContent = jContent;
    }
}