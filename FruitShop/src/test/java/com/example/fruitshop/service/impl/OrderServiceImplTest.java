package com.example.fruitshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.fruitshop.entity.order;
import com.example.fruitshop.mapper.OrderMapper;
import com.example.fruitshop.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    private OrderMapper om;

    @Autowired
    private OrderService os;

    @Test
    void add() {
        String Ono = os.add("tuser","15648562348","address1","ÀóÖ¦");

        QueryWrapper<order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Utele","15648562348");
        List<order> ls = om.selectList(queryWrapper);

        boolean flag = false;
        if(ls.get(ls.size()-1).getGname().equals("ÀóÖ¦") && ls.get(ls.size()-1).getUname().equals("tuser")){
            flag = true;
        }

        assertEquals(true,flag);

        QueryWrapper<order> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("Ono",Ono);
        om.delete(queryWrapper);
    }

    @Test
    void findList() {
        List<order> ls = os.findList();
        assertNotNull(ls);
    }

    @Test
    void findByGname() {
        List<order> ls = os.findByGname("ÀóÖ¦");
        boolean flag = true;
        for(int i=0;i<ls.size();i++){
            if(!ls.get(i).getGname().equals("ÀóÖ¦")){
                flag=false;
            }
        }
        assertEquals(true,flag);
    }

    @Test
    void findByOno() {
        order o = os.findByOno("O00001");
        assertNotNull(o);
    }

    @Test
    void close() {
        os.close("O00001");

        QueryWrapper<order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Ono","O00001");
        order o = om.selectOne(queryWrapper);

        assertEquals("off",o.getOstate());

        o.setOstate("on");
        UpdateWrapper<order> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("Ono","O00001");
        om.update(o,updateWrapper);
    }
}