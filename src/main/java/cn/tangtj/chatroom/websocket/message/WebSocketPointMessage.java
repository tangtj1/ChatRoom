package cn.tangtj.chatroom.websocket.message;

/**
 * @author tang
 */
public class WebSocketPointMessage {

    private String msg;
    private int type;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
