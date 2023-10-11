package com.example.fruitshop.service;

import com.example.fruitshop.entity.goods;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface GoodsService {

    void add(String Gname, String Gdescription,float Gprice, MultipartFile photo) throws IOException;

    List<goods> findList();

    List<goods> findHistory();

    void upline(String name);

    void offline(String name);

    void freeze(String name);

    void delete(String name);
}
