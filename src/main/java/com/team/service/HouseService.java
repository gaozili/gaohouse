package com.team.service;

import com.github.pagehelper.PageInfo;
import com.team.entity.House;

import com.team.entity.Users;
import com.team.utils.Page;

import java.util.List;

public interface HouseService {
    PageInfo<House> getHouseByPage(Page page);
    int addhouse(House house);
    PageInfo<House> getHouseByUsersID(Integer id,Page page);
    House getHouse(String id);
    int updateHouse(House house);
    int delHouse(String id,Integer isdel);

}
