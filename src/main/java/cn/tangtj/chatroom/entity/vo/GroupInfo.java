package cn.tangtj.chatroom.entity.vo;

import java.util.List;

/**
 * @author tang
 */
public class GroupInfo {
    private String groupKey;
    private String groupName;
    private int liveUserCount;
    private int maxSizeofUser;
    private List<String> users;

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getLiveUserCount() {
        return liveUserCount;
    }

    public void setLiveUserCount(int liveUserCount) {
        this.liveUserCount = liveUserCount;
    }

    public int getMaxSizeofUser() {
        return maxSizeofUser;
    }

    public void setMaxSizeofUser(int maxSizeofUser) {
        this.maxSizeofUser = maxSizeofUser;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public List<String> getUsers() {
        return users;
    }
}
