package com.zfkj.handbook.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zfkj.handbook.mapper.DocumentMapper;
import com.zfkj.handbook.pojo.entity.Document;
import com.zfkj.handbook.service.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    DocumentMapper documentMapper;
    private static final int size = 3;//定义每页的总条数

    //查询某个分类下的文档(接收一个当前页和一个分类参数)
    @Override
    public List<Document> listDocByCid(int current, int cId) {
        Page<Document> mpPage = documentMapper.selectPage(new Page<>(current, size), Wrappers.<Document>query().eq("c_id", cId));
        List<Document> listDoc = mpPage.getRecords();
        return listDoc;
    }

    //查询全部分类下(不包括分类6：常用表格)标题或内容包含关键字的文档（接受一个关键字）
    @Override
    public List<Document> listDocByKey(int current, String keyValue) {
        Page<Document> mpPage = documentMapper.selectPage(new Page<>(current, size), Wrappers.<Document>query()
                .lt("c_id", 6).and(i -> i.like("d_name", keyValue).or().like("d_content", keyValue)));
        List<Document> listDoc = mpPage.getRecords();
        return listDoc;
    }

    //查询某个分类下标题或内容包含关键字的文档（接受一个分类和关键字）
    @Override
    public List<Document> listDocByCidAndKey(int current, int cId, String keyValue) {
        Page<Document> mpPage = documentMapper.selectPage(new Page<>(current, size), Wrappers.<Document>query()
                .eq("c_id", cId).and(i -> i.like("d_name", keyValue).or().like("d_content", keyValue)));
        List<Document> listDoc = mpPage.getRecords();
        return listDoc;
    }

    //根据文档ID查询指定文档（接受一个文档ID）
    @Override
    public Document queryDocByDid(int dId) {
        Document document = documentMapper.selectById(dId);
        return document;
    }
}
