package com.zfd.spzx.manager.task;

import cn.hutool.core.date.DateUtil;
import com.zfd.spzx.manager.mapper.OrderInfoMapper;
import com.zfd.spzx.manager.mapper.OrderStatisticsMapper;
import com.zfd.spzx.model.entity.order.OrderStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OrderStatisticsTask {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;

    @Scheduled(cron = "0 0 2 * * ?")
    //@Scheduled(cron = "0/10 * * * * ?")
    public void orderTotalAmountStatistics() {

        String createDate = DateUtil.offsetDay(new Date(),-1).toString("yyyy-MM-dd");

        OrderStatistics orderStatistics = orderInfoMapper.selectStatisticsByDate(createDate);

        if (orderStatistics != null) {
            orderStatisticsMapper.insert(orderStatistics);
        }
    }

}
