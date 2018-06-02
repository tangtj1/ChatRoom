package cn.tangtj.chatroom.websocket;

import cn.tangtj.chatroom.dao.GroupDao;
import cn.tangtj.chatroom.entity.Group;
import cn.tangtj.chatroom.entity.vo.UserSessionInfo;
import cn.tangtj.chatroom.utils.JsonUtil;
import cn.tangtj.chatroom.websocket.message.TextMessageMsgType;
import cn.tangtj.chatroom.websocket.message.UserChatMessage.UserChatMessage;
import cn.tangtj.chatroom.websocket.message.WebSocketPointMessage;
import cn.tangtj.chatroom.websocket.message.broadcast.BroadcastManger;
import cn.tangtj.chatroom.websocket.message.broadcast.BroadcastMessage;
import cn.tangtj.chatroom.websocket.message.broadcast.BroadcastOrder;
import cn.tangtj.chatroom.websocket.message.broadcast.BroadcastType;
import cn.tangtj.chatroom.websocket.util.WebSocketHandlerKey;
import cn.tangtj.chatroom.websocket.util.WebSocketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Optional;

/**
 * @author tang
 */
public class ChatSocketHandler  extends TextWebSocketHandler {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private BroadcastManger bcManger;

    /***
     * TODO
     * 接收到消息时的处理函数
     * @param session
     * @param message
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message){
        WebSocketPointMessage pointMsg = JsonUtil.json2Obj(message.getPayload(), WebSocketPointMessage.class);
        UserSessionInfo info = WebSocketUtil.getInfoFromSessionAttr(session, WebSocketHandlerKey.INFO_KEY);
        if (pointMsg == null || info == null) {
            return;
        }
        System.out.println(message.getPayload());
        if (WebSocketUtil.isHeartPack(pointMsg)) {
            BroadcastMessage bcm = bcManger.createBcFactory(BroadcastOrder.HEART, BroadcastType.USER);
            bcManger.send(bcm, info.getUsername());
        } else if (pointMsg.getType() == TextMessageMsgType.CHAT) {
            UserChatMessage msg = WebSocketUtil.coverToUserChatMessage(pointMsg.getMsg(), info, TextMessageMsgType.CHAT);
            groupDao.sendMessageToGroup(msg, info.getGroupKey());
        }
    }

    /***
     * TODO
     * 客户端链接时的处理函数
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String groupKey = WebSocketUtil.getInfoFromSessionAttr(session,WebSocketHandlerKey.GROUP_KEY);
        if (groupKey != null){
            Group group = groupDao.getGroup(groupKey);
            if (group != null && !group.isContains(session)) {
                BroadcastMessage bcm = bcManger.createBcFactory(BroadcastOrder.UP_INFO, BroadcastType.GROUP);
                bcManger.send(bcm, group.getKey());
                group.addUser(session);
            }
        }
    }

    /**
     * todo
     * 关闭链接时的处理函数
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Optional<String> optional = WebSocketUtil.getInfoFromSessionAttr(session, WebSocketHandlerKey.GROUP_KEY, String.class);
        optional.ifPresent(u -> {
            Group group = groupDao.getGroup(u);
            if (group != null) {
                BroadcastMessage bcm = bcManger.createBcFactory(BroadcastOrder.UP_INFO, BroadcastType.GROUP);
                bcManger.send(bcm, group.getKey());
                group.removeUser(session);
                if (session.isOpen()) {
                    try {
                        session.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
