package com.team.protal.controller;

import com.team.entity.Type;
import com.team.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("TypeController2")
@RequestMapping("/page/")
public class TypeController {
    @Autowired
    private TypeService typeService;
    @RequestMapping("getType")
    @ResponseBody
    public List<Type> getType(){
        return typeService.getAllType();
    }


}
