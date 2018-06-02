package cn.tangtj.chatroom.websocket.util;

import cn.tangtj.chatroom.entity.vo.UserSessionInfo;
import cn.tangtj.chatroom.websocket.message.TextMessageMsgType;
import cn.tangtj.chatroom.websocket.message.UserChatMessage.UserChatMessage;
import cn.tangtj.chatroom.websocket.message.WebSocketPointMessage;
import cn.tangtj.chatroom.websocket.message.broadcast.BroadcastOrder;
import org.springframework.web.socket.WebSocketSession;

import java.util.Date;
import java.util.Map;
import java.util.Optional;


/**
 * @author tang
 */
public final class WebSocketUtil {

    public static <T> Optional<T> getInfoFromSessionAttr(WebSocketSession session, Object key, Class<T> cls) {
        Map map = session.getAttributes();
        Object obj = map.get(key);
        if (obj != null) {
            T t = cls.cast(obj);
            return Optional.of(t);
        }
        return Optional.empty();
    }

    public static <T> T getInfoFromSessionAttr(WebSocketSession session, Object key) {
        Map map = session.getAttributes();
        Object obj = map.get(key);
        return (T) obj;
    }

    public static void addInfoIntoSessionAttr(WebSocketSession session, String key, Object value) {
        Map<String, Object> map = session.getAttributes();
        map.put(key, value);
    }

    public static UserChatMessage coverToUserChatMessage(String msg, UserSessionInfo info, int msgType) {
        UserChatMessage message = new UserChatMessage();
        message.setAuthor(info.getUsername());
        message.setGroupKey(info.getGroupKey());
        message.setTime(new Date());
        message.setMsgType(msgType);
        message.setMsg(msg);
        return message;
    }

    public static boolean isHeartPack(WebSocketPointMessage pointMessage) {
        if (pointMessage != null && pointMessage.getType() == TextMessageMsgType.BROADCAST
                && pointMessage.getMsg().equals(BroadcastOrder.HEART)) {
            return true;
        }
        return false;
    }

}
