package com.zfkj.handbook.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.zfkj.handbook.mapper.JournalismMapper;
import com.zfkj.handbook.pojo.entity.Journalism;
import com.zfkj.handbook.service.JournalismService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalismServiceImpl implements JournalismService {

    @Autowired
    JournalismMapper journalismMapper;

    @Override
    public List<Journalism> listJournalism() {
        List<Journalism> journalisms = journalismMapper.selectList(null);
        if (CollectionUtils.isNotEmpty(journalisms)) {
            return journalisms;
        }
        return null;
    }

    @Override
    public Journalism updateJournalism(Journalism journalism) {
        int i = journalismMapper.updateById(journalism);
        if (i > 0) {
            return queryJournalismByJId(journalism.getJId());
        }
        return null;
    }

    @Override
    public Journalism queryJournalismByJId(int jId) {
        Journalism journalism = journalismMapper.selectById(jId);
        if (ObjectUtils.isNotEmpty(journalism)) {
            return journalism;
        }
        return null;
    }
}
