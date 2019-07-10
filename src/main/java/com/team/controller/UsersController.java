package com.team.controller;

import com.github.pagehelper.PageInfo;
import com.team.entity.Users;
import com.team.service.UsersService;
import com.team.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @RequestMapping("getUsers")
    @ResponseBody
    public Map<String, Object> getUsers(Page page) {
        PageInfo<Users> info = usersService.getUsersByPage(page);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", info.getTotal());
        map.put("rows", info.getList());
        return map;
    }




    @RequestMapping("getSingleUsers")
    @ResponseBody
    public Users getSingleUsers(Integer id) {

        return usersService.getUsers(id);
    }

}