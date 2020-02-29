package com.service.impl;


import com.entity.TicketEntity;
import com.mapper.TicketMapper;
import com.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        if(ticketEntity == null){
            return null;
        }
        return ticketEntity;
    }

    @Override
    public List<TicketEntity> getTicketByBooker(String ticketbooker) {
        List<TicketEntity> list = ticketMapper.getTicketByBooker(ticketbooker);
        if(list.size() == 0){
            return null;
        }
        return list;
    }

    @Override
    public List<TicketEntity> getTicketByUser(String ticketbelong) {
        List<TicketEntity> list = ticketMapper.getTicketByUser(ticketbelong);
        if(list.size() == 0){
            return null;
        }
        return list;
    }

    @Override
    public int getTicketByInTime(String strInTime, String strOutTime) {
        Date intime =  timesTempToDate(strInTime);
        Date outtime =  timesTempToDate(strOutTime);
        List<TicketEntity> list = ticketMapper.getTicketByInTime(intime,outtime);
        return list.size();
    }

    @Override
    public int getTicketByOutTime(String strInTime, String strOutTime) {
        Date intime =  timesTempToDate(strInTime);
        Date outtime =  timesTempToDate(strOutTime);
        List<TicketEntity> list = ticketMapper.getTicketByOutTime(intime,outtime);
        return list.size();
    }

    @Override
    public List findUserByTime(String strStartTime, String strEndTime) {
        //进入园区人员数量
        int inUserNum = getTicketByInTime(strStartTime, strEndTime);
        //离开园区人员数量
        int outUserNum =getTicketByOutTime(strStartTime, strEndTime);
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
        List<TicketEntity> ticketList= ticketMapper.list();
        return ticketList;
    }

    @Override
    public List businessData() {
        List<TicketEntity> ticketList= ticketMapper.list();
        int usedFee = 0;
        int unusedFee = 0;
        int unusedUserNum = 0;
        for(TicketEntity ticket: ticketList){
            if(ticket.getIsused() == TicketEntity.TICKET_USED){
                //计算已经使用后的门票总费用
                usedFee = usedFee +ticket.getTicketprice();
            }else {
                unusedFee = unusedFee +ticket.getTicketprice();
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
        dataList.add(usedFee+unusedFee);
        return dataList;
    }

    @Override
    public Date timesTempToDate(String strTimesTemp) {
        long lt = new Long(strTimesTemp);
        Date date = new Date(lt);
        return date;
    }


}
