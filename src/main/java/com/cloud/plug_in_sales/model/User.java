package com.cloud.plug_in_sales.model;

import lombok.Data;

import java.sql.Date;

/**
 * @author 张玉雷
 */
@Data
public class User {

    /**
     * 用户id
     */
    private int uid;
    /**
     * 用户qq号码
     */
    private String qqNumber;
    /**
     * 用户购买数量
     */
    private int ucount;
    /**
     * 用户最新的购买时间
     */
    private String utime;
}
