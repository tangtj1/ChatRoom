package cn.tangtj.chatroom.dao;

import cn.tangtj.chatroom.entity.Group;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
