package com.zfkj.handbook.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zfkj.handbook.pojo.entity.Notice;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface NoticeMapper extends BaseMapper<Notice> {

    @Select("select n_id,n_title,n_content from notice where n_id = 1")
    Notice queryNotice();

}
