package com.cloud.plug_in_sales.service.impl;

import com.cloud.plug_in_sales.mapper.GoodsMapper;
import com.cloud.plug_in_sales.model.User;
import com.cloud.plug_in_sales.service.IndexService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张玉雷
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public String getPwd(int count) {
        List<String> pwds = goodsMapper.getPwdByCount(count);
        StringBuilder builder = new StringBuilder();
        for (String pwd : pwds) {
            builder.append(pwd).append("\n");
            goodsMapper.updateStatusByPwd(pwd);
        }
        return builder.toString();
    }

    @Override
    public void saveUser(User user) {
        goodsMapper.saveUser(user);
    }

    @Override
    public int getSum() {
        return goodsMapper.getSum();
    }


}
