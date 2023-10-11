package com.example.fruitshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.fruitshop.mapper.ShopMapper;
import com.example.fruitshop.service.ShopService;
import com.example.fruitshop.entity.shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper sm;

    @Override
    public void add(String name, String account, String password) {
        shop s = new shop(name,account,password);
        sm.insert(s);
        return;
    }

    @Override
    public void updatePassword(String account,String password) {
        shop s;

        QueryWrapper<shop> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Saccount",account);
        s = sm.selectOne(queryWrapper);

        s.setSpassword(password);

        UpdateWrapper<shop> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("Saccount",account);
        sm.update(s,updateWrapper);
    }

    @Override
    public shop findByAccount(String account) {
        QueryWrapper<shop> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Saccount",account);
        shop s = sm.selectOne(queryWrapper);
        return s;
    }
}
