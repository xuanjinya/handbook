package com.zfkj.handbook.service;

import com.zfkj.handbook.pojo.entity.Horn;
import com.zfkj.handbook.pojo.entity.Notice;

public interface HornService {
    //查询公告
    Horn queryHorn();

    //增加公告
    //更新公告
    Horn updateHorn(Horn horn);
    //删除公告
}
