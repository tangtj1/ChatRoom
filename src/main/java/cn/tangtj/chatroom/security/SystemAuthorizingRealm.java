package cn.tangtj.chatroom.security;

import cn.tangtj.chatroom.entity.User;
import cn.tangtj.chatroom.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * 系统登录及其权限处理
 *
 * @author tang
 */
@Slf4j
@Component
public class SystemAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        log.info("用户尝试登录:{}",username);
        User user = userService.findByUsername(username);
        if (user == null){
            throw new UnknownAccountException();
        }
        if (!password.equals(user.getPassword())){
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(user,password,getName());
    }
}
