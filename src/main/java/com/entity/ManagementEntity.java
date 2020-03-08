package com.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 景区经营数据类
 * */
@Data
public class ManagementEntity {

    /**
     * 其中list的 size 分别表示周、月、年度数据
     *
     * 周数据：size = 7
     * 月数据：size = 30
     * 年数据：size = 12
     * */

    private List<Integer> ticketSellTotalNum;  //景区门票售出总数
    private List<Integer> ticketSellTotalPrice;   //门票售出总营业额

    private List<Integer> ticketSellUsedNum; //售出门票中已使用的门票数量
    private List<Integer> ticketSellUsedPrice; //已使用的门票对应营业额

    private List<Integer> ticketSellUnusedNum;  //售出门票中未使用的门票数量
    private List<Integer> ticketSellUnusedPrice; //未使用的门票对应营业额



}
