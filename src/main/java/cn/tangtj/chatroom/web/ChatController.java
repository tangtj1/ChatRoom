package cn.tangtj.chatroom.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tang
 */
@Controller
@RequestMapping("/chat")
public class ChatController {

    @RequestMapping()
    public String chatIndex() {
        return "chat";
    }
}
