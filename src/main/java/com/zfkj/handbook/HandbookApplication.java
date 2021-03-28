package com.zfkj.handbook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zfkj.handbook.mapper")
public class HandbookApplication {

    /* 默认启动类 */
    public static void main(String[] args) {
        SpringApplication.run(HandbookApplication.class, args);
    }

}
