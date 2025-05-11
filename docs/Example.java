package docs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * cholog-logger 실전 예제
 * - application.yml에 필수 설정을 추가한 뒤 실행하세요.
 * - 로그가 중앙 서버로 전송되는지 ELK 등에서 확인할 수 있습니다.
 */
public class Example {
    private static final Logger log = LoggerFactory.getLogger(Example.class);

    public static void main(String[] args) {
        log.info("Hello, CHOLOG! 실전 로그가 중앙 서버로 전송됩니다.");
    }
} 