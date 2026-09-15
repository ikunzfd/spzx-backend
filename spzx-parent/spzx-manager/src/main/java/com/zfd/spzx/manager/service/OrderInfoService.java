package com.zfd.spzx.manager.service;

import com.zfd.spzx.model.dto.order.OrderStatisticsDto;
import com.zfd.spzx.model.vo.order.OrderStatisticsVo;

public interface OrderInfoService {
    OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto);
}
