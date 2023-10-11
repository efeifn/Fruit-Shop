package com.example.fruitshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.fruitshop.mapper.GoodsMapper;
import com.example.fruitshop.service.GoodsService;
import com.example.fruitshop.entity.goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper gm;

    @Override
    //增加商品
    public void add(String Gname, String Gdescription, float Gprice, MultipartFile photo) throws IOException {
        List<goods> ls = gm.selectList(null);
        String a = ls.get(ls.size()-1).getGno();
        a = a.substring(1);
        int t = Integer.parseInt(a)+1;
        String Gno;
        if(t/10==0){
            Gno = "G0000"+t;
        }else if(t/100==0){
            Gno = "G000"+t;
        }else if(t/1000==0){
            Gno = "G00"+t;
        }else if(t/10000==0){
            Gno = "G0"+t;
        }else if(t/100000==0){
            Gno = "G"+t;
        }else{
            System.out.println("商品已满");
            return;
        }

        String path = System.getProperty("user.dir")+"/images/";    //存储至项目内文件夹
        saveFile(Gname,photo,path);

        String houzhui = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf("."));
        String Gphoto = Gname+houzhui;

        goods g = new goods(Gno,Gname,Gdescription,Gphoto,Gprice,"on");
        gm.insert(g);
        return;
    }

    private void saveFile(String goodsName, MultipartFile photo, String path) throws IOException {
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdir();
        }

        String houzhui = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf("."));
        File file = new File(path+goodsName+houzhui);
        photo.transferTo(file);
    }

    @Override
    public List<goods> findList() {
        List<goods> ls = gm.selectList(null);
        return ls;
    }

    @Override
    public List<goods> findHistory() {
        QueryWrapper<goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Gstate","off");
        List<goods> ls = gm.selectList(queryWrapper);
        return ls;
    }

    @Override
    public void upline(String name) {
        goods g;

        QueryWrapper<goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Gname",name);
        g = gm.selectOne(queryWrapper);

        g.setGstate("on");

        UpdateWrapper<goods> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("Gname",name);
        gm.update(g,updateWrapper);
        return;
    }

    @Override
    public void offline(String name) {
        goods g;

        QueryWrapper<goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Gname",name);
        g = gm.selectOne(queryWrapper);

        g.setGstate("off");

        UpdateWrapper<goods> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("Gname",name);
        gm.update(g,updateWrapper);
        return;
    }

    @Override
    public void freeze(String name) {
        goods g;

        QueryWrapper<goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Gname",name);
        g = gm.selectOne(queryWrapper);

        g.setGstate("frozen");

        UpdateWrapper<goods> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("Gname",name);
        gm.update(g,updateWrapper);
        return;
    }

    @Override
    public void delete(String name) {
        QueryWrapper<goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Gname",name);
        gm.delete(queryWrapper);
        return;
    }
}
