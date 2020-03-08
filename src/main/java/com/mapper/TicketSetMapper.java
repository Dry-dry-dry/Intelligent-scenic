package com.mapper;


import com.entity.TicketSetEntity;
import com.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TicketSetMapper {

    int insert(TicketSetEntity entity);

    void delete(@Param("ticketID") Integer ticketID);


    void update(TicketSetEntity entity);


    List<TicketSetEntity> list();



}
