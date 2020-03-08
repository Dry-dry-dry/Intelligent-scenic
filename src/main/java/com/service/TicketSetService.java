package com.service;

import com.entity.TicketEntity;
import com.entity.TicketSetEntity;

import java.util.List;

public interface TicketSetService {
    void insert(TicketSetEntity entity);

    void delete(String id);

    void update(TicketSetEntity entity);

    List<TicketSetEntity> list();

}
