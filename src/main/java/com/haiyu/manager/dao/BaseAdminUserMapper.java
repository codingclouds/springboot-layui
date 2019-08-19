package com.haiyu.manager.dao;


import com.haiyu.manager.dto.AdminUserDTO;
import com.haiyu.manager.pojo.BaseAdminUser;
import com.haiyu.manager.dto.UserSearchDTO;
import tk.mapper.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseAdminUserMapper extends MyMapper<BaseAdminUser> {

//    Integer getUserListCount(UserSearchDTO userSearchDTO);
    Integer getUserListCount(@Param("sysUserName")String sysUserName,@Param("userPhone")String userPhone,@Param("startTime")String startTime,@Param("endTime")String endTime);

//    List<AdminUserDTO> getUserList(UserSearchDTO userSearchDTO);
    List<AdminUserDTO> getUserList(@Param("sysUserName")String sysUserName,@Param("userPhone")String userPhone,@Param("startTime")String startTime,@Param("endTime")String endTime);

    BaseAdminUser getUserByUserName(@Param("sysUserName")String sysUserName,@Param("id") Integer id);

    int updateUserStatus(@Param("id") Integer id,@Param("status") Integer status);

    int updateUser(BaseAdminUser user);

    BaseAdminUser findByUserName(@Param("userName") String userName);

    int updatePwd(@Param("userName") String userName,@Param("password") String password);

}