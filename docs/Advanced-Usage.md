# 고급 사용법 (Advanced Usage)

## MDC(Mapped Diagnostic Context) 활용

SLF4J의 MDC를 사용하면 사용자 정의 필드를 로그에 추가할 수 있습니다.

```java
import org.slf4j.MDC;

try (MDC.MDCCloseable ignored = MDC.putCloseable("custom.userId", userId)) {
    log.info("주문 처리 중...");
}
```

## 필터 순서 커스터마이징

Spring Boot의 `FilterRegistrationBean`을 사용해 필터 순서를 직접 지정할 수 있습니다.

## 로그 레벨/배치/압축 등 고급 설정

- `log-level`, `batch-size`, `compression-enabled` 등 다양한 옵션을 조정해 최적화할 수 있습니다.
- 자세한 옵션은 [Configuration](./Configuration.md) 참고

## 커스텀 로그 서버 연동

ELK 외의 자체 로그 서버와 연동하려면 HTTP JSON POST를 수신하도록 구현하면 됩니다.

## JMX를 통한 상태 모니터링

JConsole, VisualVM 등으로 `com.cholog.logger:type=LoggingMetrics` MBean을 조회할 수 있습니다. 