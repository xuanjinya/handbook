package com.zfkj.handbook.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zfkj.handbook.pojo.entity.Category;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {

    @Select("select c_id,c_name,c_info,c_url,create_time,update_time,is_delete from category")
    List<Category> listAllCategory();


}
