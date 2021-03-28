package com.zfkj.handbook.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zfkj.handbook.pojo.entity.Document;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DocumentMapper extends BaseMapper<Document> {

    //查询某个分类下的全部文档(接收一个分类参数)
    @Select("select d_id,c_id,d_name,d_content,create_time from document where c_id = #{cId} and is_delete = 0")
    List<Document> listDocByCid(int cId);

    //查询全部分类下标题或内容包含关键字的文档
    @Select("select d_id,c_id,d_name,d_content,create_time from document where d_name like #{cId} and is_delete = 0")
    List<Document> listDocByCid(String keyValue);

    //查询某个分类下标题或内容包含关键字的文档

    //根据文档ID查询指定文档

}
