package cn.tangtj.chatroom.web;

import cn.tangtj.chatroom.AjaxBodyMap;
import cn.tangtj.chatroom.entity.Group;
import cn.tangtj.chatroom.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author tang
 */
@Controller
@RequestMapping("/chat")
public class ChatController {

    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @RequestMapping()
    public String chatIndex(Model model) {
        List list = chatRoomService.getAllGroup();
        long count = chatRoomService.getOnlineUserCount();
        model.addAttribute("romeList",list);
        model.addAttribute("onlineCount",count);
        return "chat";
    }

    @ResponseBody
    @RequestMapping("/create")
    public AjaxBodyMap createChatRome(String name) {
        AjaxBodyMap body = new AjaxBodyMap();
        Group group = chatRoomService.createGroup(name);
        if (group != null) {
            body.setOk(true);
            body.addBody("msg","创建成功");
            return body;
        }
        body.setOk(false);
        body.addBody("msg","创建失败");
        return body;
    }

    @ResponseBody
    @RequestMapping("/search/{keyOrName}")
    public List<Group> searchRome(@PathVariable("keyOrName") String keyOrName){
        List<Group> list =  chatRoomService.searchGroup(keyOrName);
        return list;
    }
}
