package cn.tangtj.config;

import cn.tangtj.chatroom.security.SystemAuthorizingRealm;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Shiro配置
 *
 * @author tang
 */
@Configuration
public class ShiroConfig {

    @Bean
    public MemorySessionDAO sessionDAO(){
        return new MemorySessionDAO();
    }

    @Bean
    public DefaultWebSessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO());
        return sessionManager;
    }

    @Bean
    public SystemAuthorizingRealm realm(){
        return new SystemAuthorizingRealm();
    }

    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/chat");
        Map<String,javax.servlet.Filter> filter = bean.getFilters();
        //设置登录form filter
        filter.put("authc",new org.apache.shiro.web.filter.authc.FormAuthenticationFilter());

        HashMap<String,String> filterChina = new HashMap<>(16);
        filterChina.put("/static/**","anon");
        filterChina.put("/login","authc");
        filterChina.put("/logout","logout");
        filterChina.put("/**","authc");

        bean.setFilterChainDefinitionMap(filterChina);

        return bean;

    }
}
