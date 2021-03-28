package com.zfkj.handbook.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.louislivi.fastdep.shirojwt.shiro.FastDepShiroJwtAuthorization;
import com.zfkj.handbook.pojo.entity.User;
import com.zfkj.handbook.service.UserService;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class FastDepShiroJwtConfig extends FastDepShiroJwtAuthorization {

    @Autowired
    UserService userService;

    //因为token是根据openId来生成的，所以这里的入参是openId，可以根据openId获取用户的权限信息
    @Override
    public SimpleAuthorizationInfo getAuthorizationInfo(String openId) {
        User user = userService.queryUserByOpenId(openId);
        String roles = user.getRoles();
        Set<String> collect = new HashSet<>();
        if (!StringUtils.isBlank(roles)) {
            //用户的权限是管理员，那么也添加普通用户权限
            if (roles.equals("admin")) {
                collect.add("user");
            }
            collect.add(roles);
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        System.out.println(collect);
        simpleAuthorizationInfo.addStringPermissions(collect);
        return simpleAuthorizationInfo;
    }

}
