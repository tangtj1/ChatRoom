package cn.tangtj.chatroom.dao;

import cn.tangtj.chatroom.entity.Group;
import cn.tangtj.chatroom.websocket.message.BaseMessage;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author tang
 */
@Component
public class GroupDao {
    private Map<String,Group> groupMap;

    public GroupDao(){
        this.groupMap = new HashMap<>();
    }


    public void addGroup(Group group){
        groupMap.put(group.getKey(),group);
    }

    public Map<String,Group> getGroupMap(){
        return groupMap;
    }

    public List<Group> getAllGroup(){
        List<Group> list = new ArrayList<>();
        groupMap.forEach((k,v)->list.add(v));
        return list;
    }

    public Group getGroup(String key){
        return groupMap.get(key);
    }

    public void sendMessageToGroup(BaseMessage message, String groupKey) {
        Group group = groupMap.get(groupKey);
        if (group != null) {
            group.sendMessageToAll(message);
        }
    }

    public void sendMessageToUser(BaseMessage message, String userName){
        for (Map.Entry<String, Group> entry :groupMap.entrySet()){
            if(entry.getValue().isContainsUsername(userName)){
                entry.getValue().sendMessageToUser(message,userName);
                break;
            }
        }
    }

    public void sendMessageToAllGroup(BaseMessage message) {
        Set<String> key = groupMap.keySet();
        for (String item : key) {
            sendMessageToGroup(message, item);
        }
    }
}
