package cn.tangtj.chatroom.web;

import cn.tangtj.chatroom.UserUtils;
import cn.tangtj.chatroom.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * 登录
 *
 * @author tang
 */
@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String loginIndex(){
        User user = UserUtils.getPrincipal();
        if (user != null){
            return "redirect:/chat";
        }
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login(){
        log.info("尝试登录");
        return "redirect:/login";
    }
}
