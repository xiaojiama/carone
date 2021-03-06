package com.codermy.myspringsecurityplus.admin.service.impl;

import com.codermy.myspringsecurityplus.admin.annotation.DataPermission;
import com.codermy.myspringsecurityplus.admin.dao.RoleDao;
import com.codermy.myspringsecurityplus.admin.dao.RoleDeptDao;
import com.codermy.myspringsecurityplus.admin.dao.RoleMenuDao;
import com.codermy.myspringsecurityplus.admin.dao.RoleUserDao;
import com.codermy.myspringsecurityplus.admin.dto.RoleDto;
import com.codermy.myspringsecurityplus.admin.entity.MyRole;
import com.codermy.myspringsecurityplus.admin.entity.MyRoleUser;
import com.codermy.myspringsecurityplus.admin.service.RoleService;
import com.codermy.myspringsecurityplus.common.utils.Result;
import com.codermy.myspringsecurityplus.common.utils.ResultCode;
import com.codermy.myspringsecurityplus.common.utils.UserConstants;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author codermy
 * @createTime 2020/7/10
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleMenuDao roleMenuDao;
    @Autowired
    private RoleUserDao roleUserDao;
    @Autowired
    private RoleDeptDao roleDeptDao;

    @Override
    @DataPermission(deptAlias = "d")
    public Result<MyRole> getFuzzyRolesByPage(Integer offectPosition, Integer limit,MyRole myRole) {
        Page page = PageHelper.offsetPage(offectPosition,limit);
        List<MyRole> fuzzyRolesByPage = roleDao.getFuzzyRolesByPage(myRole);
        return Result.ok().count(page.getTotal()).data(fuzzyRolesByPage).code(ResultCode.TABLE_SUCCESS);
    }

    @Override
    public MyRole getRoleById(Integer id) {
        return roleDao.getRoleById(id);
    }

    @Override
    public Result update(RoleDto roleDto) {
        List<Integer> menuIds = roleDto.getMenuIds();
        menuIds.remove(0L);
        //1??????????????????????????????????????????????????????????????????
        roleMenuDao.deleteRoleMenu(roleDto.getRoleId());
        //2?????????????????????????????????????????????????????????"
        if (!CollectionUtils.isEmpty(menuIds)) {
            roleMenuDao.save(roleDto.getRoleId(), menuIds);
        }
        //3??????????????????
        int countData = roleDao.update(roleDto);
        if(countData > 0){
            return Result.ok().message("????????????");
        }else{
            return Result.error().message("????????????");
        }
    }

    @Override
    public Result authDataScope(RoleDto roleDto) {
        if (roleDto.getDataScope().equals(UserConstants.DATA_SCOPE_CUSTOM)){
            List<Integer> deptIds = roleDto.getDeptIds();
            deptIds.remove(0L);
            roleDeptDao.deleteRoleDept(roleDto.getRoleId());
            if (!CollectionUtils.isEmpty(deptIds)) {
                roleDeptDao.save(roleDto.getRoleId(), deptIds);
            }
            roleDao.update(roleDto);
        }else {
            roleDao.update(roleDto);
            roleDeptDao.deleteRoleDept(roleDto.getRoleId());
        }
        return Result.ok().message("????????????");
    }

    @Override
    public Result save(RoleDto roleDto) {
        roleDto.setDataScope("1");
        //1??????????????????"
        roleDao.saveRole(roleDto);
        List<Integer> menuIds = roleDto.getMenuIds();
        //??????0,permission id??????1??????
        //2????????????????????????????????????
        if (!CollectionUtils.isEmpty(menuIds)) {
            roleMenuDao.save(roleDto.getRoleId(), menuIds);
        }
        return Result.ok().message("????????????");
    }

    @Override
    public Result<MyRole> delete(Integer id) {
        List<MyRoleUser> tbRoleUsers = roleUserDao.listAllRoleUserByRoleId(id);
        if(tbRoleUsers.size() <= 0){
            roleMenuDao.deleteRoleMenu(id);
            roleDao.delete(id);
            roleDeptDao.deleteRoleDept(id);
            return Result.ok().message("????????????");
        }
        return Result.error().message("?????????????????????,????????????");
    }

    @Override
    public Result<MyRole> getAllRoles() {
        return Result.ok().data(roleDao.getAllRoles());
    }
}
