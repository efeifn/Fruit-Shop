package com.example.fruitshop.controller;

import com.example.fruitshop.service.OrderService;
import com.example.fruitshop.entity.order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService os;

    @GetMapping("/order/list")
    public List<order> findAll(){      //获取订单列表
        return os.findList();
    }

    @PostMapping("/order/findbyono")
    public order findByOno(String Ono){        //查询订单
        return os.findByOno(Ono);
    }

    @PostMapping("/order/findbygname")
    public List<order> findByGname(String Gname){      //查询购买商品意向人名单
        return os.findByGname(Gname);
    }

    @PostMapping("/order/close")
    public String close(String Ono){   //更新订单状态
        os.close(Ono);
        return "订单关闭成功";
    }

    @PostMapping("/order/add")
    public String add(String Uname,String Utele,String Uadd,String Gname){    //添加订单
        os.add(Uname,Utele,Uadd,Gname);
        return "订单添加成功";
    }

}
