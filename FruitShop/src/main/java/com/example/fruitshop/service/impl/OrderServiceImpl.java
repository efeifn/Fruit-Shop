package com.example.fruitshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.fruitshop.mapper.OrderMapper;
import com.example.fruitshop.service.OrderService;
import com.example.fruitshop.entity.order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper om;

    @Override
    public String add(String Uname, String Utele, String Uadd, String Gname) {
        List<order> ls = om.selectList(null);
        String a = ls.get(ls.size()-1).getOno();
        a = a.substring(1);
        int t = Integer.parseInt(a)+1;
        String Ono;
        if(t/10==0){
            Ono = "O0000"+t;
        }else if(t/100==0){
            Ono = "O000"+t;
        }else if(t/1000==0){
            Ono = "O00"+t;
        }else if(t/10000==0){
            Ono = "O0"+t;
        }else if(t/100000==0){
            Ono = "O"+t;
        }else{
            return "订单已满";
        }

        SimpleDateFormat f= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());

        order o = new order();
        o.setOno(Ono);
        o.setOstate("on");
        o.setUname(Uname);
        o.setUtele(Utele);
        o.setUadd(Uadd);
        o.setGname(Gname);
        o.setOtime(f.format(date));
        om.insert(o);

        return Ono;
    }

    @Override
    public List<order> findList() {
        return om.selectList(null);
    }

    @Override
    public List<order> findByGname(String Gname) {
        QueryWrapper<order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Gname",Gname);
        List<order> ls = om.selectList(queryWrapper);
        return ls;
    }

    @Override
    public order findByOno(String Ono) {
        QueryWrapper<order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Ono",Ono);
        order o = om.selectOne(queryWrapper);
        return o;
    }

    @Override
    public void close(String Ono) {
        order o;

        QueryWrapper<order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Ono",Ono);
        o = om.selectOne(queryWrapper);

        o.setOstate("off");

        UpdateWrapper<order> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("Ono",Ono);
        om.update(o,updateWrapper);
        return;
    }
}
