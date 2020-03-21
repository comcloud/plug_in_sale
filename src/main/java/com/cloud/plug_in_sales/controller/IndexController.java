package com.cloud.plug_in_sales.controller;

import com.cloud.plug_in_sales.service.IndexService;
import com.cloud.plug_in_sales.util.MailUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 张玉雷
 */
@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;

//    @Autowired
//    private Logger log;

    @RequestMapping("/")
    public String index(){
        return "purchase";
    }

    @GetMapping("/purchaseSuccess")
    public String success(HttpServletRequest request,
                          ModelAndView model){
        /**
         * 对数量进行处理
         * 1.合法字符处理
         *  根据数量查找数据库得到等同数量符合的密钥
         *  获取用户输入的qq号
         *  将密钥发送至指定qq邮箱
         * 2.保存错误信息，然后页面返回到购买页面
         * */
//        log.info("代码已经执行,正在校验数据，准备发送密钥");
        System.out.println("代码已经执行,正在校验数据，准备发送密钥");
        try {
            int count = Integer.parseInt(request.getParameter("count"));
            model.addObject("error","");
            String qqNumber = request.getParameter("qqNumber");
            String pwd = indexService.getPwd(count);
            MailUtil.send(qqNumber+"@qq.com",pwd);
//            log.info("密钥发送完毕！");
            System.out.println("密钥发送完毕！");
        }catch (Exception e){
            e.printStackTrace();
            model.addObject("error","请输入合法的数量");
//            log.info("密钥发送失败");
            System.out.println("密钥发送失败");
            return "purchase";
        }


        return "success";
    }


}
