package cn.tangtj.chatroom.web;

import cn.tangtj.chatroom.AjaxBodyMap;
import cn.tangtj.chatroom.entity.Group;
import cn.tangtj.chatroom.entity.User;
import cn.tangtj.chatroom.service.ChatRoomService;
import cn.tangtj.chatroom.service.UserService;
import cn.tangtj.chatroom.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.GET;
import java.util.List;

/**
 * @author tang
 */
@Controller
@RequestMapping("/chat")
public class ChatController {

    private final ChatRoomService chatRoomService;

    public final UserService userService;

    @Autowired
    public ChatController(ChatRoomService chatRoomService, UserService userService) {
        this.chatRoomService = chatRoomService;
        this.userService = userService;
    }

    @RequestMapping()
    public String chatIndex(Model model) {
        List list = chatRoomService.getAllGroup();
        long count = chatRoomService.getOnlineUserCount();
        User user = UserUtils.getPrincipal();
        model.addAttribute("romeList",list);
        model.addAttribute("onlineCount",count);
        model.addAttribute("user",user);
        return "chat";
    }

    @ResponseBody
    @RequestMapping("/create")
    public AjaxBodyMap createChatGroup(String name) {
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
    @RequestMapping(value = "/search/{keyOrName}",method = RequestMethod.GET)
    public List<Group> searchGroup(@PathVariable("keyOrName") String keyOrName){
        List<Group> list =  chatRoomService.searchGroup(keyOrName);
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/room",method = RequestMethod.GET)
    public List<Group> getAllGroup(){
        List<Group> list = chatRoomService.getAllGroup();
        return list;
    }

    @ResponseBody
    @RequestMapping("/changename")
    public AjaxBodyMap changename(String name) {
        AjaxBodyMap body = new AjaxBodyMap();
        User user = UserUtils.getPrincipal();
        if (user != null && name != null && (name.length() > 3 || name.length() < 30)){
            user.setNicename(name);
            user = userService.save(user);
            if (user.getNicename().equals(name)){
                body.setOk(true);
                body.addBody("msg","修改成功");
                return body;
            }
        }
        body.setOk(false);
        body.addBody("msg","修改失败");
        return body;
    }
}
