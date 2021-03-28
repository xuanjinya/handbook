package com.zfkj.handbook.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zfkj.handbook.mapper.UserMapper;
import com.zfkj.handbook.pojo.entity.User;
import com.zfkj.handbook.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    //增加用户
    @Override
    public int addUser(User user) {
        log.info("进行了增加用户操作，用户：{}", user);
        int results = userMapper.insert(user);
        return results;
    }

    // 返回0：更新失败 ，返回1：更新成功
    @Override
    public int deleteUser(int uId, int status) {
        if (status >= 0 && status <= 1) {
            log.info("对{}用户进行了{}", uId, status == 1 ? "删除" : "撤销删除");
            return userMapper.deleteUser(uId, status);
        }
        return 0;
    }

    @Override
    public int rolesUser(int uId, int status) {
        if (0 <= status && status <= 1) {
            log.info("对{}用户进行了{}", uId, status == 1 ? "授权" : "撤销授权");
            return userMapper.rolesUser(uId, status == 1 ? "user" : "");
        }
        return 0;
    }

    @Override
    public List<User> listUser(int status) {
        List<User> users = null;
        switch (status) {
            case 1://获取全部用户
                users = userMapper.listAllUser();
                log.info("获取全部用户");
                break;
            case 2://获取已经授权用户
                users = userMapper.listRolesUser();
                log.info("获取已经授权用户");
                break;
            case 3://获取未授权用户
                users = userMapper.listNoRolesUser();
                log.info("获取未授权用户");
                break;
            case 4://获取已删除用户
                users = userMapper.listDeletedUser();
                log.info("获取已删除用户");
                break;
        }
        return users;
    }

    //根据openid查询用户
    @Override
    public User queryUserByOpenId(String openId) {
        if (!StringUtils.isBlank(openId)) {
            return userMapper.queryUserByOpenId(openId);
        }
        return null;
    }
}
