package jscode.jscodestudy.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    private static final int MAX_AGE_SEC = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // CORS를 적용할 URL 패턴 정의
                .allowedOrigins("*") // 자원 공유를 허용할 Origin 지정
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE") //허용할 HTTP method 지정
                .maxAge(MAX_AGE_SEC); // 3000sec 만큼 pre-flight 요청에 대한 응답을 브라우저에서 캐싱
    }
}
