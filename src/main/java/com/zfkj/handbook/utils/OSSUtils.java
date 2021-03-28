package com.zfkj.handbook.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.InputStream;

public class OSSUtils {
    private static final String ENDPOINT = "oss-cn-hangzhou.aliyuncs.com";
    private static final String ACCESSKEYID = "";
    private static final String ACCESSKEYSECRET = "";
    private static final String BUKETNAME = "zfkj";
    private static final String SUFFER_URL = "http://zfkj.oss-cn-hangzhou.aliyuncs.com/"; //上传成功返回的URl

    public String uploadFile(InputStream inputStream, String fileName) {
        OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);
        ossClient.putObject(BUKETNAME, fileName, inputStream);
        ossClient.shutdown();
        return SUFFER_URL + fileName;
    }
}
