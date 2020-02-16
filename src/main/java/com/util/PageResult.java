package com.util;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PageResult<T> {
    private int total;
    private int pageNo;
    private int pageSize;
    private List<T> list = new ArrayList<>();

    public PageResult(int total, int pageNo, int pageSize, List<T> list) {
        this.total = total;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.list = list;
    }


    public PageResult(Pagination pageInfo) {
        this.total = 0;
        this.pageNo = pageInfo.getPageNo();
        this.pageSize = pageInfo.getPageSize();
    }

}
