package com.haiyu.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.haiyu.manager.annotation.Log;
import com.haiyu.manager.common.enums.BusiTypeEnum;
import com.haiyu.manager.common.staticparm.BusiType;
import com.haiyu.manager.common.staticparm.OperType;
import com.haiyu.manager.common.utils.DateUtils;
import com.haiyu.manager.dao.BaseAdminPermissionMapper;
import com.haiyu.manager.dao.BaseAdminRoleMapper;
import com.haiyu.manager.dto.PermissionDTO;
import com.haiyu.manager.pojo.BaseAdminPermission;
import com.haiyu.manager.pojo.BaseAdminRole;
import com.haiyu.manager.pojo.BaseAdminUser;
import com.haiyu.manager.response.PageDataResult;
import com.haiyu.manager.service.AdminPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: PermissionServiceImpl
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/11/30 9:44
 */
@CacheConfig(cacheNames = "user_permission")
@Service
public class AdminPermissionServiceImpl implements AdminPermissionService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseAdminPermissionMapper permissionMapper;

    @Autowired
    private BaseAdminRoleMapper roleMapper;

    @Log(busi = BusiType.PREMISSION_MANAGE,operType = OperType.ADD)
//    @CacheEvict(value="get_permission_list_cache",key="#permission.getName()")// 清空 get_permission_list_cache 缓存
    @Override
    public Map<String, Object> addPermission(BaseAdminPermission permission) {
        Map<String,Object> data = new HashMap();
        try {
            permission.setCreateTime(DateUtils.getCurrentDate());
            permission.setUpdateTime(DateUtils.getCurrentDate());
            permission.setDelFlag(1);
            int result = permissionMapper.insert(permission);
            if(result == 0){
                data.put("code",0);
                data.put("msg","新增失败！");
                logger.error("权限[新增]，结果=新增失败！");
                return data;
            }
            data.put("code",1);
            data.put("msg","新增成功！");
            logger.info("权限[新增]，结果=新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("权限[新增]异常！", e);
            return data;
        }
        return data;
    }

    @Log(busi = BusiType.PREMISSION_MANAGE,operType = OperType.UPDATE)
    @Override
    public Map <String, Object> updatePermission(BaseAdminPermission permission) {
        Map<String,Object> data = new HashMap();
        try{
            permission.setUpdateTime(DateUtils.getCurrentDate());
            int result = permissionMapper.updatePermission(permission);
            if(result == 0){
                data.put("code",0);
                data.put("msg","更新失败！");
                logger.error("权限[更新]，结果=更新失败！");
                return data;
            }
            data.put("code",1);
            data.put("msg","更新成功！");
            logger.info("权限[更新]，结果=更新成功！");
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("权限[更新]异常！", e);
            return data;
        }
        return data;
    }


//    @Cacheable(value = "get_permission_list_cache",key = "'permission_list_'+#pageNum+'_'+#pageSize")
    @Override
    public PageDataResult getPermissionList(Integer pageNum, Integer pageSize) {
        Integer total =permissionMapper.getPermissionCount();
        PageDataResult pageDataResult = new PageDataResult();
        PageHelper.startPage(pageNum, pageSize);
        List<PermissionDTO> permissions = permissionMapper.getPermissionList((pageNum-1)*pageSize,pageSize);

        if(permissions.size() != 0){
//            PageInfo<PermissionDTO> pageInfo = new PageInfo<>(permissions);
            pageDataResult.setList(permissions);
            pageDataResult.setTotals(total);
        }

        return pageDataResult;
    }

    @Override
    public List<PermissionDTO> parentPermissionList() {
        return permissionMapper.parentPermissionList();
    }

    @Log(busi = BusiType.PREMISSION_MANAGE,operType = OperType.DEL)
    @Override
    public Map <String, Object> del(long id) {
        Map<String, Object> data = new HashMap<>();
        try {
            // 删除权限菜单
            int result = permissionMapper.deleteByPrimaryKey(id);
            if(result == 0){
                data.put("code",0);
                data.put("msg","删除失败");
                logger.error("删除失败");
                return data;
            }
            data.put("code",1);
            data.put("msg","删除成功");
            logger.info("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除权限菜单异常！", e);
        }
        return data;
    }


    @Override
    public BaseAdminPermission getById(Object id) {
        return permissionMapper.selectByPrimaryKey(id);
    }


    @Cacheable(key = "'user_permission'+#user.roleId")
    @Override
    public Map <String, Object> getUserPerms(BaseAdminUser user) {
        Map<String, Object> data = new HashMap<>();
        Integer roleId = user.getRoleId();

        BaseAdminRole role = roleMapper.selectByPrimaryKey(roleId);
        if (null != role ) {
            String permissions = role.getPermissions();

            String[] ids = permissions.split(",");
            List<PermissionDTO> permissionList = new ArrayList <>();
            for (String id : ids) {
                // 角色对应的权限数据
                BaseAdminPermission perm = permissionMapper.selectByPrimaryKey(id);
                if (null != perm ) {
                    // 授权角色下所有权限
                    PermissionDTO permissionDTO = new PermissionDTO();
                    BeanUtils.copyProperties(perm,permissionDTO);
                    //获取子权限
                    List<PermissionDTO> childrens = permissionMapper.getPermissionListByPId(perm.getId());
                    permissionDTO.setChildrens(childrens);
                    permissionList.add(permissionDTO);
                }
            }
            data.put("perm",permissionList);
        }

        return data;
    }
}
