package com.zfkj.handbook.service;

import com.zfkj.handbook.pojo.entity.Document;

import java.util.List;

public interface DocumentService {

    //查询某个分类下的全部文档(接收一个分类参数)
    List<Document> listDocByCid(int current, int cId);

    //查询全部分类下标题或内容包含关键字的文档（接受一个关键字）
    List<Document> listDocByKey(int current, String keyValue);

    //查询某个分类下标题或内容包含关键字的文档（接受一个分类和关键字）
    List<Document> listDocByCidAndKey(int current, int cId, String keyValue);

    //根据文档ID查询指定文档（接受一个文档ID）
    Document queryDocByDid(int dId);
}
