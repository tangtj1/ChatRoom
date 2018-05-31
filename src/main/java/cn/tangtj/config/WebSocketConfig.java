package cn.tangtj.config;

import cn.tangtj.chatroom.websocket.ChatSocketHandler;
import cn.tangtj.chatroom.websocket.WebSocketInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * @author tang
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatSocketHandler(),"/chat/chatOn").addInterceptors(webSocketInterceptor());
    }

    @Bean
    public WebSocketHandler chatSocketHandler(){
        return new ChatSocketHandler();
    }

    @Bean
    public HandshakeInterceptor webSocketInterceptor(){
        return new WebSocketInterceptor();
    }
}
