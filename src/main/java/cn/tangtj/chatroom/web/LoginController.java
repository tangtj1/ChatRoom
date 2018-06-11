package cn.tangtj.chatroom.web;

import cn.tangtj.chatroom.entity.User;
import cn.tangtj.chatroom.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

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
    public String loginIndex(ServletRequest request){
        User user = UserUtils.getPrincipal();
        if (user != null){
            return "redirect:/chat";
        }
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login(HttpServletRequest request, RedirectAttributesModelMap redirectAttributesModelMap){
        String message = (String) request.getAttribute("loginErrorMsg");
        redirectAttributesModelMap.addFlashAttribute("error_msg",message);
        return "redirect:/login";
    }
}
