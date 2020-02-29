package com.mapper;


import com.entity.TicketEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TicketMapper {

    void insert(TicketEntity entity);

    void delete(@Param("id") Integer id);

    void update(TicketEntity entity);

    TicketEntity getTicketById(@Param("id") Integer id);

    List<TicketEntity> getTicketByBooker(@Param("ticketbooker") String ticketbooker);

    List<TicketEntity> getTicketByUser(@Param("ticketbelong") String ticketbelong);

    List<TicketEntity> getTicketByInTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<TicketEntity> getTicketByOutTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<TicketEntity> list();


}
