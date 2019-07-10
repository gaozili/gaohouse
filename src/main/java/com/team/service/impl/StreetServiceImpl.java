package com.team.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.entity.Street;
import com.team.entity.StreetExample;
import com.team.mapper.StreetMapper;
import com.team.service.StreetService;
import com.team.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StreetServiceImpl implements StreetService {
    @Autowired
    private StreetMapper streetMapper;

    @Override
    public PageInfo<Street> getStreetByDid(Integer id, Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());

        StreetExample streetExample=new StreetExample();
        //添加条件
        StreetExample.Criteria criteria=streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(id);  //通过区域编号查询当前街道

        List<Street> list=streetMapper.selectByExample(streetExample);
        return new PageInfo<Street>(list);
    }

    @Override
    public List<Street> getAllStreet(Integer id) {
        StreetExample example = new StreetExample();
        StreetExample.Criteria criteria = example.createCriteria();
        criteria.andDistrictIdEqualTo(id);
        List<Street> streets = streetMapper.selectByExample(example);
        return streets;
    }
}

