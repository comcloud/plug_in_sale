package com.cloud.plug_in_sales.controller;

import java.text.SimpleDateFormat;

import com.cloud.plug_in_sales.model.User;
import com.cloud.plug_in_sales.service.IndexService;
import com.cloud.plug_in_sales.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 张玉雷
 */
@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * @param model 数据显示
     * @return 支付首页
     */
    @RequestMapping("/")
    public String index(Model model) {
        int stock = indexService.getStock();
        model.addAttribute("stock", stock);
        return "purchase";
    }

    /**
     * @return 管理员发送密钥页面
     */
    @RequestMapping("/success")
    public String success() {
        return "success";
    }

    /**
     * @param request
     * @param session
     * @param model
     * @return 用户购买页面
     * 发送验证邮箱给管理员
     */
    @GetMapping("/purchaseSuccess")
    public String success(HttpServletRequest request, HttpSession session, Model model) {
        try {
            int count = Integer.parseInt(request.getParameter("count"));
            String qqNumber = request.getParameter("qqNumber");
            model.addAttribute("error", "");
            session.setAttribute("test", "测试seesion");
            MailUtil.send("2230817302@qq.com", "qq号为" + qqNumber + "购买了" + count + "个密钥,请你确认是否已经到账" + count * 148 + "元");
        } catch (MessagingException e) {
            model.addAttribute("error", "qq号有误");
            return "purcahse";
        } catch (NumberFormatException e) {
            model.addAttribute("error", "请输入合法的数量");
            return "purchase";
        }
        return "purchase";
    }

    /**
     * @param request
     * @param model
     * @return 支付页面
     * 保存用户信息，发送密钥给用户，修改对应的密钥信息
     */
    @GetMapping("/payConfirm")
    public String payConfirm(HttpServletRequest request,
                             Model model) {
        try {
            int count = Integer.parseInt(request.getParameter("count"));
            String qqNumber = request.getParameter("qqNumber");
            User user = new User();
            user.setUcount(count);
            user.setQqNumber(qqNumber);
            user.setUtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            indexService.saveUser(user);
            String pwd = indexService.getPwd(count);
            MailUtil.send(qqNumber + "@qq.com", pwd);
            model.addAttribute("sendMessage", "发送成功");
            return "success";
        } catch (MessagingException e) {
            model.addAttribute("sendMessage", "邮件发送失败，请检查qq号码是否输入正确 ");
            return "success";
        }catch (NumberFormatException e){
            model.addAttribute("sendMessage","数量输入错误");
            return "success";
        }
    }

}