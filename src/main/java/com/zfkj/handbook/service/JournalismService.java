package com.zfkj.handbook.service;

import com.zfkj.handbook.pojo.entity.Journalism;

import java.util.List;

public interface JournalismService {

    //获取所有新闻
    List<Journalism> listJournalism();

    //根据id更新新闻
    Journalism updateJournalism(Journalism journalism);

    //根据id查询journalism
    Journalism queryJournalismByJId(int jId);

}
