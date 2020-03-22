package com.cloud.plug_in_sales.service;

import com.cloud.plug_in_sales.model.User;

/**
 * @author 张玉雷
 */
public interface IndexService {

    /**
     * @param count 密钥数量
     * @return 密钥信息
     */
    String getPwd(int count);

    /**
     * @param user 保存的用户
     */
    void saveUser(User user);

    /**
     * @return 获取数据中可以用的密钥数量
     */
    int getSum();
}
