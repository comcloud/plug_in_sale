package com.cloud.plug_in_sales.controller;
import	java.text.SimpleDateFormat;

import com.cloud.plug_in_sales.model.User;
import com.cloud.plug_in_sales.service.IndexService;
import com.cloud.plug_in_sales.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Date;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 张玉雷
 */
@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping("/")
    public String index(){
        ModelAndView modelAndView = new ModelAndView();
        int sum = indexService.getSum();
        modelAndView.addObject("sum",sum);
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
        try {
            int count = Integer.parseInt(request.getParameter("count"));
            String qqNumber = request.getParameter("qqNumber");
            User user = new User();
            user.setUcount(count);
            user.setQqNumber(qqNumber);
            user.setUtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            indexService.saveUser(user);
            model.addObject("error","");
            String pwd = indexService.getPwd(count);
            MailUtil.send(qqNumber+"@qq.com",pwd);
        }catch (MessagingException e){
            model.addObject("error","qq号有误");
            return "purchase";
        }catch (NumberFormatException e){
            model.addObject("error","请输入合法的数量");
            return "purchase";
        }
        return "success";
    }


}
