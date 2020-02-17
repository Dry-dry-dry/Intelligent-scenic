package com.entity;

import lombok.Data;

@Data
public class UserCountReq {
    private String area;//所在地区
    private String tourist;//是否参加旅行社
    private String company;//所属单位
    private String guide;//导游名字

}
