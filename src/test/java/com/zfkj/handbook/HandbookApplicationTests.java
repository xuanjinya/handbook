package com.zfkj.handbook;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.louislivi.fastdep.shirojwt.jwt.JwtUtil;
import com.zfkj.handbook.mapper.UserMapper;
import com.zfkj.handbook.pojo.entity.User;
import com.zfkj.handbook.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class HandbookApplicationTests {

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {

        IPage<User> userPage = new Page<>(1, 3);//参数一是当前页，参数二是每页个数
        userPage = userMapper.selectPage(userPage, null);
        List<User> records = userPage.getRecords();//
        long total = userPage.getTotal();//获取总条数
        for (User user : records) {
            System.out.println(user);
        }

/*        List<User> users = userMapper.listAllUser();
        System.out.println(users);*/
        /*User user  = new User();
        user.setOpenId("566666");
        user.setAvatarUrl("5d45ad");
        user.setUsername("dwad");
        int user1 = userService.addUser(user);
        System.out.println(user1);*/
        /*int res = userMapper.deleteUser(2, 1);
        System.out.println(res);*/
    }
}
