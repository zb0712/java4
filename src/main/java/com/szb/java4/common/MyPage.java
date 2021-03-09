package com.szb.java4.common;

import com.szb.java4.bean.FileFolderToShow;
import lombok.Data;

import java.util.List;

/**
 * @author 石致彬
 * @create 2021-02-24 23:19
 */
@Data
public class MyPage<T> {
    //当前页码
    private Integer current;
    //总页码
    private Integer pageTotal;
    //每页显示的数量
    private Integer pageSize;
    //总记录数
    private Integer pageTotalCount;
    //当前页的数据
    private List<T> items;

    public MyPage(Integer current, Integer pageSize) {
        this.current = current;
        this.pageSize = pageSize;
    }

    public MyPage() {
    }
}
