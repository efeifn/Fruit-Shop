package com.example.fruitshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fruitshop.entity.order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface OrderMapper extends BaseMapper<order> {
}
