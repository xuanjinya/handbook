package com.zfkj.handbook.controller;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.zfkj.handbook.pojo.dto.NoticeDTO;
import com.zfkj.handbook.pojo.entity.Notice;
import com.zfkj.handbook.service.NoticeService;
import com.zfkj.handbook.utils.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @GetMapping("/listNotice")
    public Result listNotice() {
        List<Notice> listNotice = noticeService.listNotice();
        if (CollectionUtils.isNotEmpty(listNotice)) {
            return new Result("200", "查询成功！", listNotice);
        }
        return new Result("400", "查询无数据！");
    }

    @PostMapping("/notice/updateNotice")
    @RequiresPermissions("admin")
    public Result updateNotice(@RequestBody NoticeDTO noticeDTO) {
        Integer nId = noticeDTO.getNId();
        if (nId >= 1) {
            Notice resNotice = noticeService.updateNotice(new Notice(nId, noticeDTO.getNTitle(), noticeDTO.getNUrl(), noticeDTO.getNContent()));
            if (ObjectUtils.isNotEmpty(resNotice)) {
                return new Result("200", "更新成功！", resNotice);
            }
            return new Result("400", "更新失败！");
        }
        return new Result("400", "参数不完整！");
    }

}
