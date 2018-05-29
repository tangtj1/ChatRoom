package cn.tangtj.chatroom.entity;

import lombok.Data;

import javax.websocket.Session;
import java.util.LinkedList;
import java.util.List;

/**
 * @author tang
 */
@Data
public class Group {

    private String key;
    private String name;
    private int maxUserSize;
    private int liveUserCount;

    private List<Session> users;

    public Group(String key,String name){
        this.key = key;
        this.name = name;
        this.users = new LinkedList<>();
        this.maxUserSize = 10;
    }
}
