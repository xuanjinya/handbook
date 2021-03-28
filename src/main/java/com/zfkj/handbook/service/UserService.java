package com.zfkj.handbook.service;

import com.zfkj.handbook.pojo.entity.User;

import java.util.List;

public interface UserService {

    //增加用户进数据库
    int addUser(User user);

    //删除或撤销删除
    int deleteUser(int uId, int status);

    //授权或撤销授权用户
    int rolesUser(int uId, int status);

    //查询用户
    List<User> listUser(int status);

    //通过openId查找用户
    User queryUserByOpenId(String openId);

}
