package com.team.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.entity.*;
import com.team.mapper.HouseMapper;
import com.team.mapper.StreetMapper;
import com.team.mapper.TypeMapper;
import com.team.mapper.UsersMapper;
import com.team.service.HouseService;
import com.team.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private StreetMapper streetMapper;
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public PageInfo<House> getHouseByPage(Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        HouseExample example=new HouseExample();

        List<House> list=houseMapper.selectByExample(example);
        for (House house:list){
            Integer userId = house.getUserId();
            Integer typeId = house.getTypeId();
            Integer streetId = house.getStreetId();
            Integer isdel=house.getIsdel();

            Users users = usersMapper.selectByPrimaryKey(userId);
            Type type = typeMapper.selectByPrimaryKey(typeId);
            Street street=streetMapper.selectByPrimaryKey(streetId);
            String name=type.getName();
            String name1 = users.getName();
            String name2=street.getName();
            house.setTypename(name);
            house.setUsersname(name1);
            house.setStreetname(name2);
        }
        return new PageInfo<>(list);
    }

    @Override
    public int addhouse(House house) {
        return houseMapper.insertSelective(house);
    }

    @Override
    public PageInfo<House> getHouseByUsersID(Integer id, Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        List<House> list = houseMapper.getHouseByUsersId(id);
        return new PageInfo<House>(list);
    }

    @Override
    public House getHouse(String id) {

        return houseMapper.getHouse(id);
    }

    @Override
    public int updateHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public int delHouse(String id, Integer isdel) {
        House house = new House();
        house.setId(id);
        house.setIsdel(isdel);
        return houseMapper.updateByPrimaryKeySelective(house);
    }
}
