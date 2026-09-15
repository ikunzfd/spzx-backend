package com.zfd.spzx.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.zfd.spzx.manager.mapper.OrderStatisticsMapper;
import com.zfd.spzx.manager.service.OrderInfoService;
import com.zfd.spzx.model.dto.order.OrderStatisticsDto;
import com.zfd.spzx.model.entity.order.OrderStatistics;
import com.zfd.spzx.model.vo.order.OrderStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;

    @Override
    public OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto) {

        List<OrderStatistics> orderStatisticsList = orderStatisticsMapper.selectList(orderStatisticsDto);

        List<String> dateList = orderStatisticsList.stream()
                .map(orderStatistics ->
                        DateUtil.format(orderStatistics.getOrderDate(),"yyyy-MM-dd")).collect(Collectors.toList());

        List<BigDecimal> decimalList = orderStatisticsList.stream()
                .map(OrderStatistics::getTotalAmount)
                .collect(Collectors.toList());

        OrderStatisticsVo orderStatisticsVo = new OrderStatisticsVo();
        orderStatisticsVo.setDateList(dateList);
        orderStatisticsVo.setAmountList(decimalList);
        return orderStatisticsVo;
    }
}
