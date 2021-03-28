package com.zfkj.handbook.controller;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.zfkj.handbook.pojo.entity.Category;
import com.zfkj.handbook.service.CategoryService;
import com.zfkj.handbook.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    //查询所有分类
    @GetMapping("/getAllCategory")
    public Result listAllCategory() {
        List<Category> categories = categoryService.listAllCategory();
        if (CollectionUtils.isNotEmpty(categories)) {
            return new Result("200", "查询成功！", categories);
        }
        return new Result("400", "查询失败，无数据！");
    }

}
