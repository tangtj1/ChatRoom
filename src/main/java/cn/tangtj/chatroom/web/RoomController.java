package cn.tangtj.chatroom.web;

import cn.tangtj.chatroom.entity.Group;
import cn.tangtj.chatroom.entity.User;
import cn.tangtj.chatroom.entity.vo.GroupInfo;
import cn.tangtj.chatroom.service.ChatRoomService;
import cn.tangtj.chatroom.utils.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.Map;

/**
 * @author tang
 */
@Controller
@RequestMapping("chat/room")
public class RoomController {

    private final ChatRoomService chatRoomService;

    @Autowired
    public RoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String chatRome(@PathVariable("id") String id, Model model, RedirectAttributesModelMap redirectMap){
        User user = UserUtils.getPrincipal();
        Group group = chatRoomService.getGroup(id);
        if (group == null){
            redirectMap.addFlashAttribute("error","未找到房间");
            return "redirect:/chat";
        }
        model.addAttribute("group",group);
        model.addAttribute("user",user);
        return "rome";
    }

    @RequestMapping(value = "/info",method = RequestMethod.POST)
    @ResponseBody
    public GroupInfo chatInfo(String groupKey){
        System.out.println(groupKey);
        return chatRoomService.getGroup(groupKey).getGroupInfo();
    }

    @RequestMapping("/{id}/exit")
    public String exitRome(@PathVariable("id") String id){
        chatRoomService.removeUserFromGroup(id, (User) SecurityUtils.getSubject().getPrincipal());
        return "redirect:/chat";
    }
}
