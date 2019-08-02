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
    Integer getUserListCount(String sysUserName,String userPhone,String startTime,String endTime);

//    List<AdminUserDTO> getUserList(UserSearchDTO userSearchDTO);
    List<AdminUserDTO> getUserList(String sysUserName,String userPhone,String startTime,String endTime,Integer offset,Integer pageSize);

    BaseAdminUser getUserByUserName(@Param("sysUserName")String sysUserName,@Param("id") Integer id);

    int updateUserStatus(@Param("id") Integer id,@Param("status") Integer status);

    int updateUser(BaseAdminUser user);

    BaseAdminUser findByUserName(@Param("userName") String userName);

    int updatePwd(@Param("userName") String userName,@Param("password") String password);

}