package com.team.service;

import com.github.pagehelper.PageInfo;

import com.team.entity.District;
import com.team.entity.Users;

import com.team.utils.Page;

import java.util.List;

public interface UsersService {
    public PageInfo<Users> getUsersByPage(Page page);
    public int addUsers(Users users);
    public Users getUsers(Integer id);
    public int checkUserName(String name);
    public Users usersLogin(String username,String password);
}
