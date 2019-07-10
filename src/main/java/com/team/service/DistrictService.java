package com.team.service;

import com.github.pagehelper.PageInfo;
import com.team.entity.District;
import com.team.utils.Page;

import java.util.List;

public interface DistrictService {
    public PageInfo<District> getDistrictByPage(Page page);
    public int addDistrict(District district);
    public int deleteDistrict(Integer id);
    public int delMoreDistrict(List<Integer> ids);
    public District getDistrict(Integer id);
    public int updateDistrict(District district);
    public List<District> getAllDistrict();
}
