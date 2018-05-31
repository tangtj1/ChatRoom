package cn.tangtj.chatroom.entity.vo;

import lombok.Data;

@Data
public class UserSessionInfo {
    private String username;
    private Long id;
    private String groupName;
    private String groupKey;

    public UserSessionInfo(String username, Long userId, String groupName, String groupKey) {
        this.username = username;
        this.id = userId;
        this.groupName = groupName;
        this.groupKey = groupKey;
    }
}
