package com.service.impl;


import com.entity.ManagementEntity;
import com.entity.TicketEntity;
import com.mapper.TicketMapper;
import com.service.TicketService;
<<<<<<< HEAD
//import org.junit.Test;
=======
>>>>>>> c8fac659b9325a9b5d0770062a9a1705dfd2a0c9
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public void insert(TicketEntity entity) {
        ticketMapper.insert(entity);
    }

    @Override
    public void delete(Integer id) {
        ticketMapper.delete(id);
    }

    @Override
    public void update(TicketEntity entity) {
        ticketMapper.update(entity);
    }

    @Override
    public TicketEntity getTicketById(Integer id) {
        TicketEntity ticketEntity = ticketMapper.getTicketById(id);
        if (ticketEntity == null) {
            return null;
        }
        return ticketEntity;
    }

    @Override
    public List<TicketEntity> getTicketByBooker(String ticketbooker) {
        List<TicketEntity> list = ticketMapper.getTicketByBooker(ticketbooker);
        if (list.size() == 0) {
            return null;
        }
        return list;
    }

    @Override
    public List<TicketEntity> getTicketByUser(String ticketbelong) {
        List<TicketEntity> list = ticketMapper.getTicketByUser(ticketbelong);
        if (list.size() == 0) {
            return null;
        }
        return list;
    }

    @Override
    public int getTicketByInTime(String strInTime, String strOutTime) {
        Date intime = timesTempToDate(strInTime);
        Date outtime = timesTempToDate(strOutTime);
        List<TicketEntity> list = ticketMapper.getTicketByInTime(intime, outtime);
        return list.size();
    }

    @Override
    public int getTicketByOutTime(String strInTime, String strOutTime) {
        Date intime = timesTempToDate(strInTime);
        Date outtime = timesTempToDate(strOutTime);
        List<TicketEntity> list = ticketMapper.getTicketByOutTime(intime, outtime);
        return list.size();
    }

    @Override
    public List findUserByTime(String strStartTime, String strEndTime) {
        //进入园区人员数量
        int inUserNum = getTicketByInTime(strStartTime, strEndTime);
        //离开园区人员数量
        int outUserNum = getTicketByOutTime(strStartTime, strEndTime);
        //园区剩余人员数量
        int remainUserNum = inUserNum - outUserNum;

        List numList = new ArrayList();
        numList.add(inUserNum);
        numList.add(outUserNum);
        numList.add(remainUserNum);
        return numList;
    }


    @Override
    public List<TicketEntity> list() {
        List<TicketEntity> ticketList = ticketMapper.list();
        return ticketList;
    }

    @Override
    public List businessData() {
        List<TicketEntity> ticketList = ticketMapper.list();
        int usedFee = 0;
        int unusedFee = 0;
        int unusedUserNum = 0;
        for (TicketEntity ticket : ticketList) {
            if (ticket.getIsused() == TicketEntity.TICKET_USED) {
                //计算已经使用后的门票总费用
                usedFee = usedFee + ticket.getTicketprice();
            } else {
                unusedFee = unusedFee + ticket.getTicketprice();
                unusedUserNum++;
            }
        }
        //计算已使用门票的人数
        int usedUserNum = ticketList.size() - unusedUserNum;

        List dataList = new ArrayList();
        dataList.add(usedUserNum);
        dataList.add(usedFee);
        dataList.add(unusedUserNum);
        dataList.add(unusedFee);
        dataList.add(ticketList.size());
        dataList.add(usedFee + unusedFee);
        return dataList;
    }

    /**
     * 计算两个时间段内经营数据
     * startTime: 开始时间
     * endTime： 结束时间
     */
    public Map<String, Integer> dayData(Date startTime, Date endTime) {

//        Calendar calendar = new GregorianCalendar();
//        calendar.setTime(startTime);
//        calendar.add(Calendar.DATE, +1);
//        Date endTime = calendar.getTime();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(simpleDateFormat.format(intime));
//        System.out.println(simpleDateFormat.format(outtime));

        List<TicketEntity> ticketList = ticketMapper.getTicketByInTime(startTime, endTime);
        int usedFee = 0;
        int unusedFee = 0;
        int unusedUserNum = 0;
        for (TicketEntity ticket : ticketList) {
            if (ticket.getIsused() == TicketEntity.TICKET_USED) {
                //计算已经使用后的门票总费用
                usedFee = usedFee + ticket.getTicketprice();
            } else {
                unusedFee = unusedFee + ticket.getTicketprice();
                unusedUserNum++;
            }
        }
        int usedUserNum = ticketList.size() - unusedUserNum;

        Map<String, Integer> dataMap = new HashMap<>();
        dataMap.put("ticketSellTotalNum", list().size());
        dataMap.put("ticketSellTotalPrice", usedFee + unusedFee);
        dataMap.put("ticketSellUsedNum", usedUserNum);
        dataMap.put("ticketSellUsedPrice", usedFee);
        dataMap.put("ticketSellUnusedNum", unusedUserNum);
        dataMap.put("ticketSellUnusedPrice", unusedFee);

        return dataMap;
    }

    /**
     * 获取每月经营数据
     * strYear: 年份  2020
     * strMonth： 月份  1、2、3等
     *返回值为当前月份每天的数据
     */
    @Override
    public ManagementEntity monthData(String strYear, String strMonth) {
        int year = 0;
        Date dateNow = new Date();
        if (strYear.equals("") || strYear == null) {
            strYear = String.valueOf(dateNow.getYear());
            year = Integer.parseInt(strYear);
        }

        ManagementEntity managementEntity = new ManagementEntity();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, Integer.parseInt(strMonth), 1); //设置日期为当月第一天
        int lastDay = 0;
        //判断：如果输入月份和当前月份相同，那么当前月份最后一天为今天
        if(Integer.parseInt(strMonth) == dateNow.getMonth()){
            lastDay = dateNow.getDay();
        }else {
            lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); //获取当月最后一天
        }

        List totalNumList = new ArrayList();
        List totalPriceList = new ArrayList();
        List usedNumList = new ArrayList();
        List usedPriceList = new ArrayList();
        List unusedNumList = new ArrayList();
        List unusedPriceList = new ArrayList();

        for (int day = 1; day <= lastDay; day++) {
            String strStartDate = year + "-" + Integer.parseInt(strMonth) + "-" + day;
            String strEndDate = null;
            if(day == lastDay){
                strEndDate = year + "-" + (Integer.parseInt(strMonth)+1) + "-" + 1;
            }else {
                strEndDate = year + "-" + Integer.parseInt(strMonth) + "-" + (day+1);
            }
            try {
                Date startDate = simpleDateFormat.parse(strStartDate);
                Date endDate = simpleDateFormat.parse(strEndDate);
                Map<String,Integer> dayDataMap = dayData(startDate,endDate);
                totalNumList.add(dayDataMap.get("ticketSellTotalNum"));
                totalPriceList.add(dayDataMap.get("ticketSellTotalPrice"));
                usedNumList.add( dayDataMap.get("ticketSellUsedNum"));
                usedPriceList.add(dayDataMap.get("ticketSellUsedPrice"));
                unusedNumList.add(dayDataMap.get("ticketSellUnusedNum"));
                unusedPriceList.add(dayDataMap.get("ticketSellUnusedPrice"));

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        managementEntity.setTicketSellTotalNum(totalNumList);
        managementEntity.setTicketSellTotalPrice(totalPriceList);
        managementEntity.setTicketSellUsedNum(usedNumList);
        managementEntity.setTicketSellUsedPrice(usedPriceList);
        managementEntity.setTicketSellUnusedNum(unusedNumList);
        managementEntity.setTicketSellUnusedPrice(unusedPriceList);

        return managementEntity;
    }

    /**
     * 获取每年经营数据
     * strYear: 年份  2020
     *返回值为当前年份每月总的数据
     */
    @Override
    public ManagementEntity yearData(String strYear){
        ManagementEntity managementEntity = new ManagementEntity();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        List totalNumList = new ArrayList();
        List totalPriceList = new ArrayList();
        List usedNumList = new ArrayList();
        List usedPriceList = new ArrayList();
        List unusedNumList = new ArrayList();
        List unusedPriceList = new ArrayList();

        Calendar calendar = Calendar.getInstance();
        Date dateNow = new Date();
        int year = Integer.parseInt(strYear);


        for (int month = 1; month <= 12; month++) {

            calendar.set(year, month, 1); //设置日期为当年某月1日
            int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); //获取当月最后一天
            String strStartDate = year + "-" + month + "-" + 1;
            String strEndDate = null;
            //如果年份为今年，那么获取数据的截止时间为今月今日
            if(year == dateNow.getYear() && month == dateNow.getTime()){
                strEndDate = year + "-" + month + "-" + dateNow.getDay();
            }else {
                strEndDate = year + "-" + month + "-" + lastDay;
            }

            try {
                Date startDate = simpleDateFormat.parse(strStartDate);
                Date endDate = simpleDateFormat.parse(strEndDate);
                Map<String,Integer> dayDataMap = dayData(startDate,endDate);
                totalNumList.add(dayDataMap.get("ticketSellTotalNum"));
                totalPriceList.add(dayDataMap.get("ticketSellTotalPrice"));
                usedNumList.add( dayDataMap.get("ticketSellUsedNum"));
                usedPriceList.add(dayDataMap.get("ticketSellUsedPrice"));
                unusedNumList.add(dayDataMap.get("ticketSellUnusedNum"));
                unusedPriceList.add(dayDataMap.get("ticketSellUnusedPrice"));

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        managementEntity.setTicketSellTotalNum(totalNumList);
        managementEntity.setTicketSellTotalPrice(totalPriceList);
        managementEntity.setTicketSellUsedNum(usedNumList);
        managementEntity.setTicketSellUsedPrice(usedPriceList);
        managementEntity.setTicketSellUnusedNum(unusedNumList);
        managementEntity.setTicketSellUnusedPrice(unusedPriceList);

        return managementEntity;

    }


    @Override
    public Date timesTempToDate(String strTimesTemp) {
        long lt = new Long(strTimesTemp);
        Date date = new Date(lt);
        return date;
    }


}
