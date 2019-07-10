package com.team.service;


import com.github.pagehelper.PageInfo;
import com.team.entity.Street;

import com.team.utils.Page;

import java.util.List;


public interface StreetService {

    //查询某区域下的街道
    PageInfo<Street> getStreetByDid(Integer id, Page page);
    List<Street> getAllStreet(Integer id);
}
