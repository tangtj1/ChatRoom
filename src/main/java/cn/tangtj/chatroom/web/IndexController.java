package cn.tangtj.chatroom.web;

import cn.tangtj.chatroom.utils.UserUtils;
import cn.tangtj.chatroom.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * 访问首页跳转到登录界面
 *
 * @author tang
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String index(){
        User user = UserUtils.getPrincipal();
        return "redirect:/login";
    }
}
