package com.zfkj.handbook.service.impl;

import com.zfkj.handbook.mapper.CategoryMapper;
import com.zfkj.handbook.pojo.entity.Category;
import com.zfkj.handbook.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> listAllCategory() {
        log.info("执行了查询分类！");
        return categoryMapper.listAllCategory();
    }
}
