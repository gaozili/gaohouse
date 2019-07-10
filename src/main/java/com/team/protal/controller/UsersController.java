package com.team.protal.controller;

import com.team.entity.Users;
import com.team.service.DistrictService;
import com.team.service.TypeService;
import com.team.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller("usersController2")
@RequestMapping("/page/")
public class UsersController {

    @Autowired
    private UsersService usersService;
    @RequestMapping(value = "checkUname",method = RequestMethod.POST)
    @ResponseBody
    public String checkUname(String name){
        int temp=usersService.checkUserName(name);
        System.out.println(1);
        return "{\"result\":"+temp+"}";
    }
    @RequestMapping(value = "reg",produces = "text/html;charset=UTF-8")

    public String reg(Users users) throws Exception{
        int temp = usersService.addUsers(users);
        if (temp==0){
            return "regs";
        }else {

            return "login";
        }
    }
    @RequestMapping(value = "loginAction",produces = "text/html;charset=UTF-8")
    public String loginAction(HttpSession session, String name, String password, Model model) throws Exception{
        Users users = usersService.usersLogin(name, password);
        if (users==null){
            model.addAttribute("info","用户名或密码不正确");
            return "login";
        }else {
            session.setAttribute("loginInfo",users);
            session.setMaxInactiveInterval(600);
            return "redirect:getHouse";
        }
    }

}
