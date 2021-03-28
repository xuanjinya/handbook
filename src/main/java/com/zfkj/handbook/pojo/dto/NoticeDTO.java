package com.zfkj.handbook.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDTO {
    private Integer nId;
    private String nTitle;
    private String nUrl;
    private String nContent;


}
