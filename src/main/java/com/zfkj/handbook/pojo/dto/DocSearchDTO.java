package com.zfkj.handbook.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocSearchDTO {
    private int current;//当前页
    private int cid;//分类ID
    private String keyValue;//关键字
}
