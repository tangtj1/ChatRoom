package cn.tangtj.chatroom.service;

import cn.tangtj.chatroom.dao.GroupDao;
import cn.tangtj.chatroom.entity.Group;
import cn.tangtj.chatroom.utils.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.apache.shiro.subject.support.DefaultSubjectContext.AUTHENTICATED_SESSION_KEY;

/**
 * @author tang
 */
@Service
public class ChatRoomService {

    private final GroupDao groupDao;

    private final SessionDAO sessionDAO;

    @Autowired
    public ChatRoomService(GroupDao groupDao, SessionDAO sessionDAO) {
        this.groupDao = groupDao;
        this.sessionDAO = sessionDAO;
    }

    public Group createGroup(String name) {
        Map<String, Group> map = groupDao.getGroupMap();
        int changeTime = 5;
        String groupKey;
        for (int t = 0; t < changeTime; t++) {
            groupKey = StringUtils.randomStr(6);
            if (!map.containsKey(groupKey)) {
                Group group = new Group(groupKey, name);
                groupDao.addGroup(group);
                return group;
            }
        }
        return null;
    }

    public List<Group> getAllGroup(){
        return groupDao.getAllGroup();
    }

    public long getOnlineUserCount(){
        Collection<Session> collection = sessionDAO.getActiveSessions();
        long count = collection.stream().filter(v->{
            Object check = v.getAttribute(AUTHENTICATED_SESSION_KEY);
            return Boolean.TRUE.equals(check);
        }).count();
        return count;
    }

    public List<Group> searchGroup(String str){
        Map<String,Group> map = groupDao.getGroupMap();
        String rex = "(\\w+)?" + str + "(\\w+)?";
        List<Group> list = new ArrayList<>();
        map.forEach((k,v)->{
            if (k.matches(rex)||v.getName().matches(rex)){
                list.add(v);
            }
        });
        return list;
    }
}
