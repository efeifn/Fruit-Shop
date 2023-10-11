package com.example.fruitshop.service;

import com.example.fruitshop.entity.order;

import java.util.List;

public interface OrderService {

    String add(String Uname,String Utele,
               String Uadd,String Gname);

    List<order> findList();

    List<order> findByGname(String Gname);

    order findByOno(String Ono);

    void close(String Ono);


}
