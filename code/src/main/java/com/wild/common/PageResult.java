package com.wild.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult implements Serializable {
    //总记录数
    @ApiModelProperty(value="总记录数",name="counts")
    private Integer counts = 0;
    //页大小
    @ApiModelProperty(value="页大小",name="pagesize")
    private Integer pagesize = 10;
    //总页数
    @ApiModelProperty(value="总页数",name="pages")
    private Integer pages = 0;
    //当前页码
    @ApiModelProperty(value="当前页码",name="page")
    private Integer page = 1;
    //列表
    @ApiModelProperty(value="列表",name="items")
    private List<?> items = Collections.emptyList();

    //页面统计
    @ApiModelProperty(value="页面统计数",name="totals")
    private List<TotalVo> totals = Collections.emptyList();

    public PageResult(Integer page, Integer pagesize,
                      Integer counts, List list) {
        this.page = page;
        this.pagesize = pagesize;
        this.items = list;
        this.counts = counts;
        this.pages = counts % pagesize == 0 ? counts / pagesize : counts / pagesize + 1;
    }


    public PageResult(Integer page, Integer pagesize,
                      Integer counts, List list, List<TotalVo> totals) {
        this.page = page;
        this.pagesize = pagesize;
        this.items = list;
        this.counts = counts;
        this.pages = counts % pagesize == 0 ? counts / pagesize : counts / pagesize + 1;
        this.totals = totals;
    }

}

