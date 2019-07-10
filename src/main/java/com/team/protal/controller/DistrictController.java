package com.team.protal.controller;

import com.github.pagehelper.PageInfo;
import com.team.entity.District;
import com.team.service.DistrictService;
import com.team.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("DistrictController2")
@RequestMapping("/page/")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @RequestMapping("getDistrict")
    @ResponseBody
    public List<District> getDistrict(){
        return districtService.getAllDistrict();
    }


    @RequestMapping("addDistrict")
    @ResponseBody
    public String getDistrict(District district) {
        //调用业务
        int temp = districtService.addDistrict(district);
        return "{\"result\":" + temp + "}";
    }

    @RequestMapping("delDistrict")
    @ResponseBody
    public String delDistrict(Integer id) {
        //调用业务
        int temp = districtService.deleteDistrict(id);
        return "{\"result\":" + temp + "}";
    }

    @RequestMapping("delMoreDistrict")  //?id=1,2,3,4
    @ResponseBody
    public String delMoreDistrict(String id) {//接收编号，名称
        //id=1,2,3,4
        //分割字符串
        String[] arys = id.split(",");
        //转化为List
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arys.length; i++) {
            list.add(Integer.parseInt(arys[i]));
        }
        //调用业务
        int temp = districtService.delMoreDistrict(list);
        return "{\"result\":" + temp + "}";
    }

    @RequestMapping("getSingleDistrict")
    @ResponseBody
    public District getSingleDistrict(Integer id) {
        //调用业务
        return districtService.getDistrict(id);
    }

    @RequestMapping("updateDistrict")
    @ResponseBody
    public String updateDistrict(District district) {
        //调用业务
        int temp = districtService.updateDistrict(district);
        return "{\"result\":" + temp + "}";
    }
}
