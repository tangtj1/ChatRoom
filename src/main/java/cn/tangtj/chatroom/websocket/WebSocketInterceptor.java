package cn.tangtj.chatroom.websocket;

import cn.tangtj.chatroom.UserUtils;
import cn.tangtj.chatroom.entity.User;
import cn.tangtj.chatroom.websocket.util.WebSocketHandlerKey;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

/**
 *
 * WebSocket 拦截器配置
 *
 * @author tang
 */
public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {

    /**
     *
     * 判断是否有Group_key,和是否登录，否则进行拦截
     *
     * @param request
     * @param response
     * @param wsHandler
     * @param attributes
     * @return
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        String groupKey = ((ServletServerHttpRequest) request).getServletRequest().getParameter(WebSocketHandlerKey.GROUP_KEY);
        User user = UserUtils.getPrincipal();
        if (user == null || groupKey == null || "".equals(groupKey)){
            return false;
        }
        attributes.put(WebSocketHandlerKey.GROUP_KEY,groupKey);
        attributes.put(WebSocketHandlerKey.USER_KEY,user);
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }
}
