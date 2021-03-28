package com.zfkj.handbook.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.louislivi.fastdep.shirojwt.jwt.JwtUtil;
import com.zfkj.handbook.pojo.dto.WxUserInfoDTO;
import com.zfkj.handbook.pojo.entity.User;
import com.zfkj.handbook.service.UserService;
import com.zfkj.handbook.utils.Result;
import com.zfkj.handbook.utils.WeChatUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    WeChatUtil weChatUtil;
    @Autowired
    UserService userService;

    //删除、撤销删除用户用户
    @RequiresPermissions("admin")
    @GetMapping("/user/deleteUser/uId={uId}/status={status}")
    public Result deleteUser(@PathVariable(name = "uId") int uId, @PathVariable(name = "status") int status) {
        if (status >= 0 && status <= 1) {
            int res = userService.deleteUser(uId, status);
            if (res == 1) {
                return new Result("200", "删除或撤销删除成功！");
            } else {
                return new Result("400", "删除或撤销删除失败！可能uid错误");
            }
        }
        return new Result("400", "输入数据有误！");
    }

    //授权或撤销授权用户
    @GetMapping("/user/rolesUser/uId={uId}/status={status}")
    @RequiresPermissions("admin")
    public Result rolesUser(@PathVariable(name = "uId") int uId, @PathVariable(name = "status") int status) {
        if (status >= 0 && status <= 1) {
            int res = userService.rolesUser(uId, status);
            if (res == 1) {
                return new Result("200", "授权或取消授权成功！");
            } else {
                return new Result("400", "授权或取消授权失败！可能uid错误");
            }
        }
        return new Result("400", "输入数据有误！");
    }

    //获取用户
    @GetMapping("/user/listUser/status={status}")
    @RequiresPermissions("admin")
    public Result listAllUser(@PathVariable(name = "status") int status) {
        //1：查询所有，2：查询已经授权 3：查询未授权 4：查询已删除
        if (status >= 1 && status <= 4) {
            List<User> users = userService.listUser(status);
            if (CollectionUtils.isNotEmpty(users)) {
                return new Result("200", "查询成功！", users);
            } else {
                return new Result("200", "查询无数据！");
            }
        }
        return new Result("400", "输入数据有误！");
    }

    @GetMapping("/login")
    public Result login() {
        String token = jwtUtil.sign("ov0Qw5Ria6rBVILOqKzyufyuLvH6QlWFY");
        Map<String, Object> map = new HashMap<>();
        map.put("Authorization", token);
        return new Result("200", "授权成功！", map);
    }

    //微信登录
    @PostMapping("/wxLogin")
    public Result wxLogin(@RequestBody WxUserInfoDTO wxUserInfoDTO) {
        String code = wxUserInfoDTO.getCode();
        if (!StringUtils.isBlank(code)) {
            JSONObject resUserInfo = weChatUtil.getSessionKeyOrOpenId(code);
            String openid = (String) resUserInfo.get("openid");
            if (!StringUtils.isBlank(openid)) {
                //查询数据库有没有对应openid的用户
                User resultUser = userService.queryUserByOpenId(openid);
                if (!ObjectUtils.isEmpty(resultUser)) {
                    //有此用户，进行生成token返回，登录成功
                    String token = jwtUtil.sign(resultUser.getOpenId());
                    Map<String, Object> hashMap = new HashMap<>();
                    hashMap.put("Authorization", token);
                    hashMap.put("uId", resultUser.getUId());
                    hashMap.put("userName", resultUser.getUsername());
                    hashMap.put("avatarUrl", resultUser.getAvatarUrl());
                    return new Result("200", "授权成功！", hashMap);
                } else {
                    //没有查到对应的用户，调用解密传来加密数据的方法获取用户信息
                    String iv = wxUserInfoDTO.getIv();
                    String encryptedData = wxUserInfoDTO.getEncryptedData();
                    String sessionKey = (String) resUserInfo.get("session_key");
                    if (!StringUtils.isBlank(encryptedData) && !StringUtils.isBlank(sessionKey) && !StringUtils.isBlank(iv)) {
                        JSONObject userInfo = weChatUtil.getUserInfo(encryptedData, sessionKey, iv);
                        String nickName = (String) userInfo.get("nickName");
                        String avatarUrl = (String) userInfo.get("avatarUrl");
                        if (!StringUtils.isBlank(nickName) && !StringUtils.isBlank(avatarUrl)) {
                            User user = new User(openid, nickName, avatarUrl);
                            int result = userService.addUser(user);
                            if (result > 0) {
                                String token = jwtUtil.sign(openid);
                                Map<String, Object> hashMap = new HashMap<>();
                                hashMap.put("Authorization", token);
                                hashMap.put("uId", user.getUId());
                                hashMap.put("userName", nickName);
                                hashMap.put("avatarUrl", avatarUrl);
                                return new Result("200", "授权成功！", hashMap);
                            } else {
                                return new Result("4003", "用户数据插入错误！", null);
                            }
                        } else {
                            //从微信后台获取的用户数据失败
                            return new Result("4002", "从微信后台获取的用户数据失败！nickName或者avatarUrl为空！", userInfo);
                        }
                    } else {
                        return new Result("4001", "用户数据不完整！", wxUserInfoDTO);
                    }
                }
            } else {
                return new Result("4001", "传入的code失效了，请重新授权！", wxUserInfoDTO);
            }
        } else {
            return new Result("4002", "传入的code为空！", wxUserInfoDTO);
        }
    }
}
