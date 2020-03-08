package com.service;

import com.entity.ManagementEntity;
import com.entity.TicketEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TicketService {
    void insert(TicketEntity entity);

    void delete(Integer id);

    void update(TicketEntity entity);

    TicketEntity getTicketById(Integer id);

    List<TicketEntity> getTicketByBooker(String ticketbooker);

    List<TicketEntity> getTicketByUser(String ticketbelong);

    int getTicketByInTime(String intime, String outtime);

    int getTicketByOutTime(String intime,String outtime);

    List findUserByTime(String strStartTime, String strEndTime);

    List<TicketEntity> list();

    List businessData();

    Map<String, Integer> dayData(Date startTime, Date endTime);

    ManagementEntity yearData(String strYear);

    ManagementEntity monthData(String strYear, String strMonth);

    Date timesTempToDate(String strTimesTemp);

}
