# 시작하기 (Getting Started)

cholog-logger는 Spring Boot 애플리케이션을 위한 중앙 집중형 로깅 SDK입니다.

---

## 1. 설치 및 의존성 추가

### Gradle
```gradle
dependencies {
    implementation 'com.ssafy.lab.eddy1219:cholog-logger:v1.8.6'
}
```

### Maven
```xml
<dependency>
  <groupId>com.ssafy.lab.eddy1219</groupId>
  <artifactId>cholog-logger</artifactId>
  <version>v1.8.6</version>
</dependency>
```

---

## 2. 필수 설정

`application.yml` 또는 `application.properties`에 아래 항목을 반드시 지정해야 합니다.

```yaml
cholog:
  logger:
    url: http://your-log-server.com/api/logs      # 중앙 로그 서버 URL (필수)
    api-key: your-api-key                         # 서비스 식별용 API 키 (필수)
    service-name: my-awesome-service              # 서비스 논리 이름 (필수)
    environment: production                       # 실행 환경 (예: production, development)
```

---

## 3. 선택적 설정 예시

```yaml
cholog:
  logger:
    log-level: INFO
    batch-size: 100
    disk-queue-enabled: true
    sensitive-patterns:
      - password
      - card
```

---

## 4. 간단한 사용 예제

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Example {
    private static final Logger log = LoggerFactory.getLogger(Example.class);
    public void doSomething() {
        log.info("Hello, CHOLOG!");
    }
}
```

---

## 5. 전체 흐름

1. 의존성 추가 및 설정 적용
2. 기존 SLF4J 로그 코드 그대로 사용
3. 로그가 자동으로 중앙 서버로 전송됨
4. ELK 등에서 로그를 실시간으로 확인 가능

---

## 6. 추가 문서
- [설정 옵션 전체 보기](./Configuration.md)
- [고급 사용법](./Advanced-Usage.md)
- [ELK 연동 가이드](./ELK-Integration.md)
- [문제 해결](./Troubleshooting.md)
- [FAQ](./FAQ.md) 