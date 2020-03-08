package com.service.impl;


import com.entity.TicketEntity;
import com.entity.TicketSetEntity;
import com.mapper.TicketMapper;
import com.mapper.TicketSetMapper;
import com.service.TicketService;
import com.service.TicketSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketSetServiceImpl implements TicketSetService {

    @Autowired
    TicketSetMapper ticketSetMapper;

    @Override
    public void insert(TicketSetEntity entity) {
        ticketSetMapper.insert(entity);
    }

    @Override
    public void delete(String ticketID) {
        int id = Integer.parseInt(ticketID);
        ticketSetMapper.delete(id);
    }

    @Override
    public void update(TicketSetEntity entity) {
        ticketSetMapper.update(entity);
    }

    @Override
    public List<TicketSetEntity> list() {

        return ticketSetMapper.list();
    }
}
