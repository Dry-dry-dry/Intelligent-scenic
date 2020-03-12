package com.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TicketEntity {
    public final static int TICKET_USED = 1;
    public final static int TICKET_UNUSED = 0;


    private int id;
    private String ticketsname; //门票名
    private int ticketprice;   //门票价格
    private String ticketbooker; //门票预订人
    private String ticketbelong; //门票拥有人
    private Date applytime;  //预订时间
    private String strapplytime;
    private Date tickettime; //门票时间
    private String strtickettime;
    private Date intime;  //进入景区时间
    private String strintime;
    private Date outtime;  //离开景区时间
    private String strouttime;

    /**
     * 说明：下面参数判断是否的值，一律如下：
     * 是：1   否：0
     * */
    private int isrefund; //是否退票
    private int isindividualt; //是否散客票
    private int isteam; //是否参加团队，是：人数，不是：0
    private int isconcession; //是否优惠票
    private int isafterbuy; //是否补票
    private int isused; //是否已使用


}
