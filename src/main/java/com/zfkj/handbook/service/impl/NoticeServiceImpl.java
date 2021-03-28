package com.zfkj.handbook.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.zfkj.handbook.mapper.NoticeMapper;
import com.zfkj.handbook.pojo.entity.Notice;
import com.zfkj.handbook.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public List<Notice> listNotice() {
        List<Notice> notices = noticeMapper.selectList(null);
        if (CollectionUtils.isNotEmpty(notices)) {
            return notices;
        }
        return null;
    }

    @Override
    public Notice updateNotice(Notice notice) {
        int i = noticeMapper.updateById(notice);
        if (i > 0) {
            return queryNoticeByNId(notice.getNId());
        }
        return null;
    }

    @Override
    public Notice queryNoticeByNId(int nId) {
        Notice notice = noticeMapper.selectById(nId);
        if (ObjectUtils.isNotEmpty(notice)) {
            return notice;
        }
        return null;
    }
}
