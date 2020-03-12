package com.entity;

import lombok.Data;

import java.util.Date;
@Data
public class HotelOrderEntity {
    private int id;
    private int hotelid;
    private int roomid;
    private Date applytime;
    private Date starttime;
    private Date endtime;
    private int ischeckout;
    private Date checkouttime;
    private int isend;
    private int ordercancel;
    private Integer userid;

    private String username;
    private String ordertext;

}
