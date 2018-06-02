package cn.tangtj.chatroom.websocket.message.UserChatMessage;

import cn.tangtj.chatroom.websocket.message.BaseMessage;

/**
 * @author tang
 */
public class UserChatMessage extends BaseMessage {

    @Override
    public String toString() {
        return "UserChatMessage{" +
                "author='" + author + '\'' +
                ", groupKey='" + groupKey + '\'' +
                ", msgType=" + msgType +
                ", time=" + time +
                ", msg='" + msg + '\'' +
                '}';
    }



}
