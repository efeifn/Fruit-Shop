package com.example.fruitshop.service;

import com.example.fruitshop.entity.shop;

public interface ShopService {

    void add(String name,String account,
             String password);

    void updatePassword(String account,
                        String password);

    shop findByAccount(String account);
}
