package com.zfkj.handbook.controller;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.zfkj.handbook.pojo.dto.JournalismDTO;
import com.zfkj.handbook.pojo.entity.Journalism;
import com.zfkj.handbook.service.JournalismService;
import com.zfkj.handbook.utils.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JournalismController {

    @Autowired
    JournalismService journalismService;

    @GetMapping("/listJournalism")
    public Result listJournalism() {
        List<Journalism> listJournalism = journalismService.listJournalism();
        if (CollectionUtils.isNotEmpty(listJournalism)) {
            return new Result("200", "查询成功！", listJournalism);
        }
        return new Result("400", "查询无数据！");
    }

    @PostMapping("/jou/updateJournalism")
    @RequiresPermissions("admin")
    public Result updateJournalism(@RequestBody JournalismDTO journalismDTO) {
        Integer jId = journalismDTO.getJId();
        if (jId >= 1 && jId <= 5) {
            Journalism journalism = new Journalism(jId, journalismDTO.getJTitle(), journalismDTO.getJUrl(), journalismDTO.getJContent());
            Journalism resJournalism = journalismService.updateJournalism(journalism);
            if (ObjectUtils.isNotEmpty(resJournalism)) {
                return new Result("200", "更新成功！", resJournalism);
            }
            return new Result("400", "更新失败！");
        }
        return new Result("400", "参数不完整！");
    }
}
