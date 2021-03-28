package com.zfkj.handbook.service;
import com.zfkj.handbook.pojo.entity.Notice;

import java.util.List;

public interface NoticeService {

    //获取所有公告
    List<Notice> listNotice();

    //根据id更新公告
    Notice updateNotice(Notice notice);

    //根据id查询Notice
    Notice queryNoticeByNId(int nId);

}
