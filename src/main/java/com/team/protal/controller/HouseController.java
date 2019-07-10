package com.team.protal.controller;

import com.github.pagehelper.PageInfo;
import com.team.entity.District;
import com.team.entity.House;
import com.team.entity.Type;
import com.team.entity.Users;
import com.team.mapper.HouseMapper;
import com.team.mapper.UsersMapper;
import com.team.service.DistrictService;
import com.team.service.HouseService;
import com.team.service.TypeService;
import com.team.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/page/")
public class HouseController {
    @Autowired
    private HouseService houseService;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private TypeService typeService;
    @Autowired
    private DistrictService districtService;
    @RequestMapping("gofabu")
    public String gofabu(Model model){
        List<Type> types=typeService.getAllType();
        List<District> districts = districtService.getAllDistrict();
        model.addAttribute("types",types);
        model.addAttribute("districts",districts);
        return "fabu";
    }
    @RequestMapping("addhouse")
   public String addHouse(HttpSession session, House house, @RequestParam(name = "pfile",required = false) CommonsMultipartFile pfile) throws  Exception{
        //1.实现图片上传：图片在图片服务器 d:/images
        String filename=pfile.getOriginalFilename();  //1.jpg  上传文件名称
        String expname=filename.substring(filename.lastIndexOf("."));  //上传文件的扩展名
        String saveFileName=System.currentTimeMillis()+expname;  //保存文件名称
        String path="d:/images/"+saveFileName;  //保存路径
        File saveFile=new File(path);
        pfile.transferTo(saveFile);  //上传文件
        System.out.println(saveFileName);

        //2.将输入的数据保存到数据库中
        house.setId(System.currentTimeMillis()+"");  //设置编号
        //设置用户id
        Users user=(Users)session.getAttribute("loginInfo");
        house.setUserId(user.getId());
        //设置图片
        house.setPath(saveFileName);

        house.setIsdel(0);  //如果数据有默认值可不设
        house.setIspass(0);  //如果数据有默认值可不设
        //调用业务
        int temp=houseService.addhouse(house);
        if(temp>0){
            return "guanli";
        }else{
            saveFile.delete();
        }
        return "redirect:goFaBu";
    }
    @RequestMapping("getHouse")

    public String getHouse(HttpSession session,Model model,Page page) throws Exception{

        Integer uid = ((Users) session.getAttribute("loginInfo")).getId();
        PageInfo<House> pageInfo=houseService.getHouseByUsersID(uid,page);
        model.addAttribute("pageInfo",pageInfo);
        return "guanli";
    }
    @RequestMapping("goUpdate")
    public String goUpdate(String id,Model model) throws  Exception{
        House house = houseService.getHouse(id);
        model.addAttribute("house",house);
        return "upfabu";
    }
    @RequestMapping("UpdateHouse")
    public String UpdateHouse(String oldPath,House house,@RequestParam(name = "pfile",required = false) CommonsMultipartFile pfile) throws  Exception{
        String filename=pfile.getOriginalFilename();
        if (filename.equals("")){
        }else {
            String expname = filename.substring(filename.lastIndexOf("."));
            String saveFileName=System.currentTimeMillis()+expname;  //保存文件名称
            String path="d:/images/"+saveFileName;  //保存路径   替换原图
            File saveFile=new File(path);
            pfile.transferTo(saveFile);  //上传文件新图
            new File("d://images//"+oldPath).delete();

            //3.设置新图片
            house.setPath(saveFileName);
        }

        //更新数据库
        houseService.updateHouse(house);

        return "redirect:getHouse";
    }
@RequestMapping("delHouse")
    public String delHouse(String id) throws Exception{
        houseService.delHouse(id,1);
        return "redirect:getHouse";
}

}
