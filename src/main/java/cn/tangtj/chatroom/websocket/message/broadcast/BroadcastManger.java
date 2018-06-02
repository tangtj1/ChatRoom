package cn.tangtj.chatroom.websocket.message.broadcast;


import cn.tangtj.chatroom.dao.GroupDao;
import cn.tangtj.chatroom.websocket.message.TextMessageMsgType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static cn.tangtj.chatroom.websocket.message.broadcast.BroadcastType.GROUP;
import static cn.tangtj.chatroom.websocket.message.broadcast.BroadcastType.SYSTEM;
import static cn.tangtj.chatroom.websocket.message.broadcast.BroadcastType.USER;

/**
 * @author tang
 */
@Component
public class BroadcastManger {

    private final GroupDao groupDao;

    @Autowired
    public BroadcastManger(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public BroadcastMessage createBcFactory(String bcStr, int type) {
        BroadcastMessage msg = new BroadcastMessage();
        msg.setMsg(bcStr);
        msg.setMsgType(TextMessageMsgType.BROADCAST);
        msg.setBcType(type);
        msg.setTime(new Date());
        return msg;
    }

    public void send(BroadcastMessage msg, String receiver) {
        switch (msg.getBcType()) {
            case USER:
                groupDao.sendMessageToUser(msg, receiver);
                break;
            case GROUP:
                groupDao.sendMessageToGroup(msg, receiver);
                break;
            case SYSTEM:
                groupDao.sendMessageToAllGroup(msg);
                break;
            default:
                break;
        }
    }
}
