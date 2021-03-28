package com.zfkj.handbook.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalismDTO {

    private Integer jId;
    private String jTitle;
    private String jUrl;
    private String jContent;

}
