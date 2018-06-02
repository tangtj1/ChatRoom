package cn.tangtj.chatroom.websocket.message;

import cn.tangtj.chatroom.utils.JsonUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.socket.TextMessage;

import java.util.Date;

/**
 * @author tang
 */
public abstract class BaseMessage {

    protected String author;
    protected String groupKey;

    protected int msgType;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm", locale = "zh", timezone = "GMT+8")
    protected Date time;
    protected String msg;


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public void setGroup(String group) {
        this.groupKey = group;
    }

    public Date getTime() {
        if (time == null) {
            return null;
        }
        return (Date) time.clone();
    }

    public void setTime(Date time) {
        this.time = (Date) time.clone();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }


    protected String toJson() {
        return JsonUtil.obj2Json(this);
    }

    public TextMessage toTextMessage() {
        return new TextMessage(this.toJson());
    }
}
