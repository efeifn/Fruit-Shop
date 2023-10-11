package com.example.fruitshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.fruitshop.entity.shop;
import com.example.fruitshop.mapper.ShopMapper;
import com.example.fruitshop.service.ShopService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
class ShopServiceImplTest {

    @Autowired
    private ShopMapper sm;

    @Autowired
    private ShopService ss;

    @Test
    void add() {
        ss.add("tname","taccount","tpassword");

        QueryWrapper<shop> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Saccount","taccount");
        shop s = sm.selectOne(queryWrapper);

        assertNotNull(s);

        sm.delete(queryWrapper);
    }

    @Test
    void updatePassword() {
        shop s = new shop("tname","taccount","tpassword");
        sm.insert(s);

        ss.updatePassword("taccount","ttpassword");

        QueryWrapper<shop> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Saccount","taccount");
        shop s2 = sm.selectOne(queryWrapper);

        assertEquals("ttpassword",s2.getSpassword());

        sm.delete(queryWrapper);
    }

    @Test
    void findByAccount() {
        shop s = new shop("tname","taccount","tpassword");
        sm.insert(s);

        ss.findByAccount("taccount");

        assertNotNull(s);

        QueryWrapper<shop> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Saccount","taccount");
        sm.delete(queryWrapper);
    }
}






