package dev.websocket.base;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker // 웹 소켓 서버를 사용하겠다는 설정
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    // WebSocketMessageBrokerConfigurer: 웹 소켓에 관련된 몇 가지 기능을 구현

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/sub");
        registry.setApplicationDestinationPrefixes("/pub");
    }

    // 클라이언트에서 웹소켓에 접속하는 endpoint를 등록
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp")
                .setAllowedOriginPatterns("http://localhost:3000")
                .withSockJS();
    }
}