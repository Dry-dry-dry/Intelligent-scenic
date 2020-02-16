package com.util;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class Pagination {
    @ApiModelProperty(value = "页码", name = "pageNo", example = "1")
    @NotNull
    @Min(1)
    private Integer pageNo;
    @ApiModelProperty(value = "页面大小", name = "pageSize", example = "10")
    @NotNull
    @Min(1)
    private Integer pageSize;
}
