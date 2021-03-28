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
public class Notice implements Serializable {
    private static final long serialVersionUID = -40373553940582114L;
    @TableId(type = IdType.AUTO)
    private Integer nId;
    private String nTitle;
    private String nUrl;
    private String nContent;
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private Object isDelete;

    public Notice(Integer nId, String nTitle, String nUrl, String nContent) {
        this.nId = nId;
        this.nTitle = nTitle;
        this.nUrl = nUrl;
        this.nContent = nContent;
    }
}
