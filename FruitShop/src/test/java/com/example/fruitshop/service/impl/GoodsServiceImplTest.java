package com.example.fruitshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.fruitshop.entity.goods;
import com.example.fruitshop.mapper.GoodsMapper;
import com.example.fruitshop.service.GoodsService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
class GoodsServiceImplTest {

    @Autowired
    private GoodsService gs;

    @Autowired
    private GoodsMapper gm;

    @Test
    void add() throws IOException {
        File file = new File("D:/FruitShop/images/test/123.png");
        MultipartFile photo = new MockMultipartFile(
                "123.png", //文件名
                "123.png", //originalName 相当于上传文件在客户机上的文件名
                "image/png", //文件类型
                new FileInputStream(file) //文件流
        );

        gs.add("tgname","tgdes",19,photo);

        QueryWrapper<goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Gname","tgname");
        goods g = gm.selectOne(queryWrapper);

        assertNotNull(g);

        gm.delete(queryWrapper);
        File f = new File("D:/FruitShop/images/tgname.png");
        f.deleteOnExit();
    }

    @Test
    void findList() {
        List<goods> ls = gs.findList();
        assertNotNull(ls);
    }

    @Test
    void findHistory() {
        List<goods> ls = gs.findHistory();
        boolean flag = true;
        for(int i=0;i<ls.size();i++){
            if(!ls.get(i).getGstate().equals("off")){
                flag = false;
            }
        }
        if(ls==null){
            flag = false;
        }
        assertEquals(true,flag);
    }

    @Test
    void upline() {
        QueryWrapper<goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Gstate","off");
        List<goods> ls = gm.selectList(queryWrapper);
        goods g = ls.get(0);

        gs.upline(g.getGname());

        goods g1;
        QueryWrapper<goods> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("Gname",g.getGname());
        g1 = gm.selectOne(queryWrapper2);

        assertEquals("on",g1.getGstate());

        UpdateWrapper<goods> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("Gname",g.getGname());
        gm.update(g,updateWrapper);
    }

    @Test
    void offline() {
        QueryWrapper<goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Gstate","on");
        List<goods> ls = gm.selectList(queryWrapper);
        goods g = ls.get(0);

        gs.offline(g.getGname());
        goods g1;
        QueryWrapper<goods> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("Gname",g.getGname());
        g1 = gm.selectOne(queryWrapper2);

        assertEquals("off",g1.getGstate());

        UpdateWrapper<goods> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("Gname",g.getGname());
        gm.update(g,updateWrapper);
    }

    @Test
    void freeze() {
        QueryWrapper<goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Gstate","on");
        List<goods> ls = gm.selectList(queryWrapper);
        goods g = ls.get(0);

        gs.freeze(g.getGname());
        goods g1;
        QueryWrapper<goods> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("Gname",g.getGname());
        g1 = gm.selectOne(queryWrapper2);

        assertEquals("frozen",g1.getGstate());

        UpdateWrapper<goods> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("Gname",g.getGname());
        gm.update(g,updateWrapper);
    }

    @Test
    void delete() {
        List<goods> ls = gm.selectList(null);
        goods g = ls.get(0);

        gs.delete(g.getGname());

        QueryWrapper<goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Gname",g.getGname());
        goods g1 = gm.selectOne(queryWrapper);

        assertNull(g1);

        gm.insert(g);
    }
}