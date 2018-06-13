package cn.tangtj.chatroom.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author tang
 */
@Slf4j
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        String className = e.getClass().getName();
        String message = "系统出现故障，请稍后再试";
        if (IncorrectCredentialsException.class.getName().equals(className)) {
            message = "帐号或密码错误，请重试";
        } else if (UnknownAccountException.class.getName().equals(className)) {
            message = "帐号不存在，请重试";
        }
        log.info("用户{}登录失败,\"{}\"",token.getPrincipal(),message);
        request.setAttribute("loginErrorMsg", message);
        return super.onLoginFailure(token, e, request, response);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        log.info("用户{},登录成功",token.getPrincipal());
        WebUtils.issueRedirect(request,response,getSuccessUrl());
        return false;
    }
}
