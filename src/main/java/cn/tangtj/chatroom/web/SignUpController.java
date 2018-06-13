package cn.tangtj.chatroom.web;

import cn.tangtj.chatroom.entity.User;
import cn.tangtj.chatroom.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

/**
 * @author tang
 */
@Slf4j
@Controller
@RequestMapping("/signup")
public class SignUpController {

    private final UserService userService;

    @Autowired
    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "signup";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String sign(String username, String password, String repassword, RedirectAttributesModelMap map) {
        if (username.length() < 5 || password.length() < 5){
            map.addFlashAttribute("message","用户名或密码不符合规范");
        }
        if (!password.equals(repassword)) {
            map.addFlashAttribute("message", "两次密码不一致");
            return "redirect:/signup";
        }
        User user = userService.findByUsername(username);
        if (user != null) {
            map.addFlashAttribute("message", "用户名不可用");
            return "redirect:/signup";
        }
        user = new User();
        user.setUsername(username);
        user.setNicename(username);
        user.setPassword(password);
        User result =  userService.save(user);
        if (result.getId() == null){
            map.addFlashAttribute("message","注册失败，请重试");
        }
        map.addFlashAttribute("success","注册成功");
        return "redirect:/signup";
    }


    @ResponseBody
    @RequestMapping("/check")
    public String checkUsername(String username) {
        User user = userService.findByUsername(username);
        boolean result = true;
        if (user != null) {
            result = false;
        }
        return "{\"valid\":\"" + result + "\"}";
    }
}
