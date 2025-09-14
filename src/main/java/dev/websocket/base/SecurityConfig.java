package dev.websocket.base;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 개발 편의: CSRF 비활성화 (운영 전에 경로별로 좁혀주세요)
                .csrf(csrf -> csrf.disable())

                // 폼 로그인/기본 인증 비활성화 (원치 않는 /login 차단)
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())

                .authorizeHttpRequests(auth -> auth
                        // 채팅 REST API 허용
                        .requestMatchers(HttpMethod.GET, "/chat/rooms").permitAll()
                        .requestMatchers("/chat/**").permitAll()

                        // WebSocket/SockJS/STOMP 허용
                        .requestMatchers("/ws-stomp/**", "/ws-stomp", "/pub/**", "/sub/**").permitAll()

                        // 정적 리소스/헬스체크 등 필요시 추가
                        .requestMatchers("/", "/index.html", "/actuator/**").permitAll()

                        // 그 외는 전부 허용(개발용). 운영에선 authenticated()로 바꾸세요.
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}