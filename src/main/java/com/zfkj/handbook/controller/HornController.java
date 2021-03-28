package com.zfkj.handbook.controller;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.zfkj.handbook.pojo.dto.HornDTO;
import com.zfkj.handbook.pojo.entity.Horn;
import com.zfkj.handbook.pojo.entity.Notice;
import com.zfkj.handbook.service.HornService;
import com.zfkj.handbook.service.NoticeService;
import com.zfkj.handbook.utils.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HornController {

    @Autowired
    HornService hornService;

    @GetMapping("/getHorn")
    public Result queryHorn() {
        Horn horn = hornService.queryHorn();
        if (!ObjectUtils.isEmpty(horn)) {
            return new Result("200", "查询成功！", horn);
        }
        return new Result("400", "查询失败！");
    }

    @PostMapping("/horn/updateHorn")
    @RequiresPermissions("admin")
    public Result updateHorn(@RequestBody HornDTO HornDTO) {
        Horn resHorn = hornService.updateHorn(new Horn(1, HornDTO.getHTitle(), HornDTO.getHContent()));
        return new Result("200", "修改成功！", resHorn);
    }
}
