package com.zfkj.handbook.service.impl;

import com.zfkj.handbook.mapper.HornMapper;
import com.zfkj.handbook.mapper.NoticeMapper;
import com.zfkj.handbook.pojo.entity.Horn;
import com.zfkj.handbook.pojo.entity.Notice;
import com.zfkj.handbook.service.HornService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HornServiceImpl implements HornService {

    @Autowired
    HornMapper hornMapper;

    @Override
    public Horn queryHorn() {
        Horn horn = hornMapper.selectById(1);
        return horn;
    }

    @Override
    public Horn updateHorn(Horn horn) {
        if (hornMapper.updateById(horn) > 0) {
            return queryHorn();
        }
        return null;
    }
}
