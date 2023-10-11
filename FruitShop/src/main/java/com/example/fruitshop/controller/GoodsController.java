package com.example.fruitshop.controller;

import com.example.fruitshop.service.GoodsService;
import com.example.fruitshop.entity.goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class GoodsController {

    @Autowired
    GoodsService gsi;

    @GetMapping("/goods/list")
    public List<goods> query(){     //获取所有商品列表
        System.out.println(gsi.findList());
        return gsi.findList();
    }

    @GetMapping("/goods/history")
    public List<goods> queryHistory(){      //获取已下线商品列表
        return gsi.findHistory();
    }

    @PostMapping("/goods/upline")
    public String upline(String name){
        gsi.upline(name);
        return "重新上架成功";
    }

    @PostMapping("/goods/offline")
    public String offline(String name){        //商品下线
        List<goods> ls = gsi.findList();
        boolean flag = false;
        for(int i=0;i<ls.size();i++){
            if(ls.get(i).getGname().equals(name)){
                flag = true;
                break;
            }
        }
        if(flag){
            gsi.offline(name);
            return "商品下线成功";
        }
        return "商品不存在";
    }

    @PostMapping("/goods/freeze")
    public String freeze(String name){
        gsi.freeze(name);
        return "商品冻结成功";
    }

    @PostMapping("/goods/add")
    //增加商品
    public String add(String Gname, String Gdescription, float Gprice, MultipartFile photo) throws IOException {
        gsi.add(Gname,Gdescription,Gprice,photo);
        return "商品添加成功";
    }

}
