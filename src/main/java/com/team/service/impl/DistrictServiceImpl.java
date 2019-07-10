package com.team.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.mapper.DistrictMapper;

import com.team.entity.District;
import com.team.entity.DistrictExample;
import com.team.service.DistrictService;
import com.team.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictMapper districtMapper;
    @Override
    public PageInfo<District> getDistrictByPage(Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        DistrictExample example=new DistrictExample();
        List<District> list = districtMapper.selectByExample(example);
        return new PageInfo<District>(list);
    }

    @Override
    public int addDistrict(District district) {
        return districtMapper.insertSelective(district);
    }

    @Override
    public int deleteDistrict(Integer id) {
        return districtMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delMoreDistrict(List<Integer> ids) {
        return districtMapper.delMoreDistrict(ids);
    }

    @Override
    public District getDistrict(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }

    @Override
    public List<District> getAllDistrict() {
        return districtMapper.selectByExample(new DistrictExample());
    }

}
