package com.zfkj.handbook.controller;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zfkj.handbook.pojo.dto.DocSearchDTO;
import com.zfkj.handbook.pojo.entity.Document;
import com.zfkj.handbook.service.DocumentService;
import com.zfkj.handbook.utils.OSSUtils;
import com.zfkj.handbook.utils.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
public class DocumentController {

    @Autowired
    DocumentService documentService;

    //根据分类，分页查找文档
    @PostMapping("/doc/listDocByCid")
    @RequiresPermissions("user")
    public Result listDocByCid(@RequestBody DocSearchDTO docSearchDTO) {
        int current = docSearchDTO.getCurrent();
        int cId = docSearchDTO.getCid();
        if (current >= 0 && (cId >= 1 && cId <= 6)) {
            List<Document> listDoc = documentService.listDocByCid(current, cId);
            if (CollectionUtils.isNotEmpty(listDoc)) {
                return new Result("200", "查询成功！", listDoc);
            } else {
                return new Result("200", "查无数据！");
            }
        }
        return new Result("400", "参数输入错误！");
    }

    //查询全部分类下标题或内容包含关键字的文档（接受一个关键字）
    @PostMapping("/doc/listDocByKey")
    @RequiresPermissions("user")
    public Result listDocByKey(@RequestBody DocSearchDTO docSearchDTO) {
        int current = docSearchDTO.getCurrent();
        String keyValue = docSearchDTO.getKeyValue();
        if (current >= 0 && !StringUtils.isBlank(keyValue)) {
            List<Document> listDoc = documentService.listDocByKey(current, keyValue);
            if (CollectionUtils.isNotEmpty(listDoc)) {
                return new Result("200", "查询成功！", listDoc);
            } else {
                return new Result("200", "查无数据！");
            }
        }
        return new Result("400", "参数输入错误！");
    }

    //查询某个分类下包括指定关键字的文档
    @PostMapping("/doc/listDocByCidAndKey")
    @RequiresPermissions("user")
    public Result listDocByCidAndKey(@RequestBody DocSearchDTO docSearchDTO) {
        int current = docSearchDTO.getCurrent();
        int cId = docSearchDTO.getCid();
        String keyValue = docSearchDTO.getKeyValue();
        if (current >= 0 && (cId >= 1 && cId <= 6) && !StringUtils.isBlank(keyValue)) {
            List<Document> listDoc = documentService.listDocByCidAndKey(current, cId, keyValue);
            if (CollectionUtils.isNotEmpty(listDoc)) {
                return new Result("200", "查询成功！", listDoc);
            } else {
                return new Result("200", "查无数据！");
            }
        }
        return new Result("400", "参数输入错误！");
    }

    //根据文档ID查询指定的文档
    @GetMapping("/doc/dId={dId}")
    @RequiresPermissions("user")
    public Result queryDocByDid(@PathVariable(name = "dId") int dId) {
        Document document = documentService.queryDocByDid(dId);
        if (!ObjectUtils.isEmpty(document)) {
            return new Result("200", "查询成功!", document);
        }
        return new Result("400", "查询失败!");
    }

    //上传文件
    @PostMapping("/doc/upload")
    @RequiresPermissions("user")
    public Result fileupload(MultipartFile file) {
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        try {
            String resUrl = new OSSUtils().uploadFile(file.getInputStream(), newName);
            return new Result("200", "上传成功！", resUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result("400", "上传失败！");
    }
}
