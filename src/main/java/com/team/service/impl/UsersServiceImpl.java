package com.team.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.team.entity.Users;
import com.team.entity.UsersExample;
import com.team.mapper.UsersMapper;
import com.team.service.UsersService;
import com.team.utils.MD5Utils;
import com.team.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public PageInfo<Users> getUsersByPage(Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        UsersExample example=new UsersExample();
        List<Users> list = usersMapper.selectByExample(example);
        return new PageInfo<Users>(list);
    }

    @Override
    public int addUsers(Users users) {
        users.setIsadmin(0);
        String s = MD5Utils.md5Encrypt(users.getPassword());
        users.setPassword(s);
        return usersMapper.insertSelective(users);
    }



    @Override
    public Users getUsers(Integer id) {
        return usersMapper.selectByPrimaryKey(id);
    }



    @Override
    public Users usersLogin(String username, String password) {
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria=example.createCriteria();
        criteria.andNameEqualTo(username);
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        List<Users> list = usersMapper.selectByExample(example);
        if (list.size()==0){
            return null;
        }else {
            return list.get(0);
        }

    }


    @Override
    public int checkUserName(String name) {
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andIsadminEqualTo(0);
        List<Users> list = usersMapper.selectByExample(example);
        return list.size();
    }
}
