package cn.tangtj.chatroom.entity.vo;

import lombok.Data;

@Data
public class UserSessionInfo {
    private String username;
    private Long id;
    private String groupName;
    private String groupKey;
    private String nicename;

    public UserSessionInfo(String username, String nicename, Long userId, String groupName, String groupKey) {
        this.username = username;
        this.nicename = nicename;
        this.id = userId;
        this.groupName = groupName;
        this.groupKey = groupKey;
    }
}
