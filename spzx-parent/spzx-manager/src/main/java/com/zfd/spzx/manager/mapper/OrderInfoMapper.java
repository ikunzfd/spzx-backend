package com.zfd.spzx.manager.mapper;

import com.zfd.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderInfoMapper {
    OrderStatistics selectStatisticsByDate(String createDate);
}
