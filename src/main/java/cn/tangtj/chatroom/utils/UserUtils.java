package cn.tangtj.chatroom.utils;

import cn.tangtj.chatroom.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @author tang
 */
public class UserUtils {

    public static User getPrincipal(){
        Subject subject = getSubject();
        if (subject.isAuthenticated()){
            return (User) subject.getPrincipal();
        }
        return null;
    }

    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }
}
