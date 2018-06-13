package cn.tangtj.chatroom.entity;

import cn.tangtj.chatroom.entity.vo.GroupInfo;
import cn.tangtj.chatroom.entity.vo.UserSessionInfo;
import cn.tangtj.chatroom.websocket.message.BaseMessage;
import cn.tangtj.chatroom.websocket.util.WebSocketHandlerKey;
import cn.tangtj.chatroom.websocket.util.WebSocketUtil;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author tang
 */
@Data
public class Group implements Serializable {

    private static final long serialVersionUID = 364472905869844354L;
    private String key;
    private String name;
    private int maxUserSize;

    private List<WebSocketSession> users;

    public Group(String key,String name){
        this.key = key;
        this.name = name;
        this.users = new ArrayList<>();
        this.maxUserSize = 10;
    }

    public void addUser(WebSocketSession session) {
        User user = WebSocketUtil.getInfoFromSessionAttr(session, WebSocketHandlerKey.USER_KEY);
        UserSessionInfo info = new UserSessionInfo(user.getUsername(),user.getNicename(),user.getId(),this.name,this.key);
        WebSocketUtil.addInfoIntoSessionAttr(session, WebSocketHandlerKey.INFO_KEY, info);
        if (!isContains(session)) {
            users.add(session);
        }
    }

    public void removeUser(WebSocketSession session) {
        users.remove(session);
    }

    public boolean isContains(WebSocketSession session){
        return users.contains(session);
    }

    public boolean isContainsUsername(String userName){
        Optional<WebSocketSession> opt =  users.stream().filter(u->{
            UserSessionInfo info =  WebSocketUtil.getInfoFromSessionAttr(u,WebSocketHandlerKey.INFO_KEY);
            if (info != null){
                return info.getUsername().equals(userName);
            }
            return false;
        }).findFirst();
        return opt.isPresent();
    }

    public List<UserSessionInfo> getAllUserInfo() {
        List<UserSessionInfo> list = new ArrayList<>();
        users.forEach(v -> list.add(WebSocketUtil.getInfoFromSessionAttr(v, WebSocketHandlerKey.INFO_KEY)));
        return list;
    }

    public void sendMessageToAll(BaseMessage msg) {
        users.forEach(v -> sendMessage(v, msg));
    }

    public void sendMessageToUser(BaseMessage msg, String userName) {
        Optional<WebSocketSession> optional = users.stream().filter(v -> {
            UserSessionInfo info = WebSocketUtil.getInfoFromSessionAttr(v, WebSocketHandlerKey.INFO_KEY);
            return info.getGroupName().equals(userName);
        }).findFirst();
        optional.ifPresent(webSocketSession -> sendMessage(webSocketSession, msg));
    }

    private void sendMessage(WebSocketSession session, BaseMessage msg) {
        if (session.isOpen()) {
            try {
                session.sendMessage(msg.toTextMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public GroupInfo getGroupInfo() {
        List<String> list = new ArrayList<>();
        this.getAllUserInfo().forEach(u -> list.add(u.getUsername()));
        GroupInfo info = new GroupInfo();
        info.setGroupKey(this.key);
        info.setGroupName(this.name);
        info.setLiveUserCount(users.size());
        info.setMaxSizeofUser(this.maxUserSize);
        info.setUsers(list);
        return info;
    }
}
