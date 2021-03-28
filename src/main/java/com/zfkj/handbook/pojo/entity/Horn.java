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
public class Horn implements Serializable {
    private static final long serialVersionUID = -40373553940580314L;
    @TableId(type = IdType.AUTO)
    private Integer hId;
    private String hTitle;
    private String hContent;
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private Object isDelete;

    public Horn(Integer hId, String hTitle, String hContent) {
        this.hId = hId;
        this.hTitle = hTitle;
        this.hContent = hContent;
    }
}
