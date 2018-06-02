package cn.tangtj.chatroom.websocket.message.broadcast;


import cn.tangtj.chatroom.websocket.message.BaseMessage;

/**
 * @author tang
 */
public class BroadcastMessage extends BaseMessage {

    private int bcType;

    public void setBcType(int bcType){
        this.bcType = bcType;
    }

    public int getBcType() {
        return bcType;
    }


    @Override
    public String toString() {
        return "BroadcastMessage{" +
                "bcType=" + bcType +
                ", author='" + author + '\'' +
                ", groupKey='" + groupKey + '\'' +
                ", msgType=" + msgType +
                ", time=" + time +
                ", msg='" + msg + '\'' +
                '}';
    }
}
