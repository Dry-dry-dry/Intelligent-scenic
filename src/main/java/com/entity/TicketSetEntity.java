package com.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TicketSetEntity {
    /**
     * 说明：下面参数判断是否的值，一律如下：
     * 是：1   否：0
     * */
    public final static int TICKET_SOLD = 1;
    public final static int TICKET_UNSOLD = 0;


    private int ticketID;

    private String ticketname; //门票名
    private int ticketprice;   //门票价格
    private int concessionprice; //门票优惠价
    private String ticketType;  //门票类型 individual(散客票),teamTicket(团队票),concession(优惠票)
    private int issale; //是否出售


}
