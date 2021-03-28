package com.zfkj.handbook.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zfkj.handbook.pojo.entity.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    @Select("select u_id,username,account,avatar_url,roles,create_time,update_time,is_delete " +
            "from user " +
            "where open_id = #{openId} and is_delete = 0")
    User queryUserByOpenId(String openId);

    @Update("update user set is_delete = #{status} where u_id = #{uId}")
    int deleteUser(int uId, int status);

    @Update("update user set roles = #{roles} where u_id = #{uId}")
    int rolesUser(int uId, String roles);

    @Select("select u_id,username,account,avatar_url,roles,create_time,update_time,is_delete " +
            "from user " +
            "where is_delete = 0")
    List<User> listAllUser();

    @Select("select u_id,username,account,avatar_url,roles,create_time,update_time,is_delete " +
            "from user " +
            "where is_delete = 0 and roles != ''")
    List<User> listRolesUser();

    @Select("select u_id,username,account,avatar_url,roles,create_time,update_time,is_delete " +
            "from user " +
            "where is_delete = 0 and roles = ''")
    List<User> listNoRolesUser();

    @Select("select u_id,username,account,avatar_url,roles,create_time,update_time,is_delete " +
            "from user " +
            "where is_delete = 1 ")
    List<User> listDeletedUser();

}
