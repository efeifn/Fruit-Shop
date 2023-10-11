package com.example.fruitshop.controller;

import com.example.fruitshop.service.ShopService;
import com.example.fruitshop.service.impl.ShopServiceImpl;
import com.example.fruitshop.entity.shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShopController {

    @Autowired
    ShopService ss;

    @PostMapping("/shop/update")
    public String update(String account,String pre,String after,String repeat){
        shop s;
        s = ss.findByAccount(account);
        if(s==null){
            return "账户不存在";
        }else if(!s.getSpassword().equals(pre)){
            return "原密码错误";
        }else if(!after.equals(repeat)){
            return "两次密码不一致";
        }else{
            s.setSpassword(after);
            ss.updatePassword(account,after);
            return "修改成功";
        }
    }

    @PostMapping("/register")
    public String addShop(String name,String account,String password,String repeat){
        if(!password.equals(repeat)){
            return "注册失败";
        }
        ss.add(name,account,password);
        return "注册成功";
    }

    @PostMapping("/login")
    public String login(String account, String password) {

        shop s = ss.findByAccount(account);

        if(s == null){
            System.out.println("用户不存在");
            return "用户不存在";
        }else if(!s.getSpassword().equals(password)){
            System.out.println("密码错误");
            return "密码错误";
        }else{
            System.out.println("登录成功");
            return "登录成功";
        }
    }
}
