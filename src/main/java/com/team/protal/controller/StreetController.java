package com.team.protal.controller;


import com.team.entity.Street;
import com.team.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("StreetController2")
@RequestMapping("/page/")
public class StreetController {

    @Autowired
    private StreetService streetService;

    @RequestMapping("getStreeByid")
    @ResponseBody
    public List<Street> getStreeByid(Integer id){
        List<Street> allStreet = streetService.getAllStreet(id);
        return allStreet;
    }

}
