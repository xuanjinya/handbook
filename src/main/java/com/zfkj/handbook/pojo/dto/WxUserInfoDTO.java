package com.zfkj.handbook.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxUserInfoDTO {
    private String code;
    private String rawData;
    private String signature;
    private String encryptedData;
    private String iv;
}

