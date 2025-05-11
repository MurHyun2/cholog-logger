# 설정 옵션 (Configuration)

**cholog-logger v1.0.0** 기준 전체 설정 옵션 안내입니다.

---

## 1. 필수 설정

아래 4가지는 반드시 지정해야 합니다.

| 옵션명 | 설명 | 예시 |
|--------|------|------|
| **url** | 로그를 전송할 중앙 서버 URL | `http://your-log-server.com/api/logs` |
| **api-key** | 서비스 식별용 API 키 | `your-api-key` |
| **service-name** | 서비스 논리 이름 | `my-awesome-service` |
| **environment** | 실행 환경 | `production` |

> **예시**
> ```yaml
> cholog:
>   logger:
>     url: http://your-log-server.com/api/logs
>     api-key: your-api-key
>     service-name: my-awesome-service
>     environment: production
> ```

---

## 2. 일반/배치/민감정보 관련 선택적 설정

| 옵션명 | 설명 | 기본값 | 예시 |
|--------|------|--------|------|
| **log-level** | 전송할 최소 로그 레벨<br/>(TRACE, DEBUG, INFO, WARN, ERROR) | INFO | INFO |
| **batch-size** | 한 번에 전송할 로그 최대 개수 | 100 | 100 |
| **batch-flush-interval** | 로그 모으는 최대 시간(ms) | 1000 | 1000 |
| **queue-capacity** | 메모리 큐 최대 용량 | 10000 | 10000 |
| **sensitive-patterns** | 민감 정보 필터링 패턴 목록 | [] | `[password, card]` |
| **sensitive-value-replacement** | 민감 정보 대체 문자열 | `***` | `***` |

> **팁:**<br/>
> - `sensitive-patterns`에 지정된 키워드는 로그에서 자동으로 마스킹됩니다.<br/>
> - 로그 레벨을 높이면(예: WARN) 전송되는 로그 양이 줄어듭니다.

---

## 3. 디스크 큐/압축/고급 설정

| 옵션명 | 설명 | 기본값 | 예시 |
|--------|------|--------|------|
| **disk-queue-enabled** | 디스크 큐 활성화 여부 | true | true |
| **disk-queue-path** | 디스크 큐 저장 경로 | ./log-queue | ./log-queue |
| **disk-resend-interval** | 디스크 큐 재전송 간격(ms) | 60000 | 60000 |
| **max-disk-queue-size-mb** | 디스크 큐 최대 크기(MB) | 1024 | 1024 |
| **compression-enabled** | 로그 압축 활성화 여부 | false | true |
| **compression-threshold** | 압축 시작 최소 크기(바이트) | 1024 | 1024 |

> **주의:**<br/>
> - 운영 환경에서는 `disk-queue-path`를 반드시 영구적이고 쓰기 가능한 경로로 지정하세요.<br/>
> - `compression-enabled: true` 사용 시 Logstash 입력에 `decompress_request => true` 옵션 필요!

---

## 4. 네트워크/재시도/보안 관련 설정

| 옵션명 | 설명 | 기본값 | 예시 |
|--------|------|--------|------|
| **max-retries** | 전송 실패 시 최대 재시도 횟수 | 3 | 3 |
| **retry-delay** | 재시도 간격(ms) | 1000 | 1000 |
| **use-https** | HTTPS 사용 여부 | false | true |
| **allow-insecure-tls** | TLS 인증서 검증 무시 여부 | false | true |
| **suppress-connection-errors** | 연결 오류 로그 최소화 | true | true |
| **max-connection-error-logs-per-period** | 기간 내 최대 연결 오류 로그 수 | 1 | 1 |
| **connection-error-log-period** | 오류 로그 제한 적용 주기(ms) | 300000 | 300000 |
| **use-exponential-backoff** | 지수 백오프 전략 사용 여부 | true | true |
| **initial-backoff-delay** | 지수 백오프 초기 지연 시간(ms) | 5000 | 5000 |
| **max-backoff-delay** | 지수 백오프 최대 지연 시간(ms) | 1800000 | 1800000 |
| **connection-check-interval** | 서버 연결 상태 확인 간격(ms) | 300000 | 300000 |
| **connection-check-timeout** | 연결 확인 요청 타임아웃(ms) | 5000 | 5000 |

> **팁:**<br/>
> - `allow-insecure-tls`는 개발 환경에서만 true 권장<br/>
> - `suppress-connection-errors`를 true로 하면 네트워크 장애 시 로그가 과도하게 쌓이지 않습니다.

---

## 5. 모니터링/메트릭스/CORS 관련 설정

| 옵션명 | 설명 | 기본값 | 예시 |
|--------|------|--------|------|
| **metrics-enabled** | 지표 수집 활성화 여부 | true | true |
| **metrics-collection-interval** | 지표 수집 간격(ms) | 60000 | 60000 |
| **expose-metrics-via-jmx** | JMX를 통한 지표 노출 여부 | true | true |
| **cors-enabled** | 기본 CORS 설정 활성화 여부 | false | true |

> **팁:**<br/>
> - JConsole, VisualVM 등으로 `com.cholog.logger:type=LoggingMetrics` MBean을 조회할 수 있습니다.<br/>
> - `cors-enabled: true`로 설정 시 모든 오리진/헤더/메소드 허용(CORS 보안 주의)

---

## 6. HTTP 클라이언트 풀 관련 설정

| 옵션명 | 설명 | 기본값 | 예시 |
|--------|------|--------|------|
| **http-client-pool-max-total** | HTTP 클라이언트 풀 최대 연결 수 | 100 | 100 |
| **http-client-pool-default-max-per-route** | 경로당 최대 연결 수 | 20 | 20 |
| **http-client-pool-evict-idle-connections-after** | 유휴 연결 제거 시간(초) | 30 | 30 |

---

## 7. 전체 설정 예시

```yaml
cholog:
  logger:
    url: http://your-log-server.com/api/logs
    api-key: your-api-key
    service-name: my-awesome-service
    environment: production
    log-level: INFO
    batch-size: 100
    batch-flush-interval: 1000
    queue-capacity: 10000
    disk-queue-enabled: true
    disk-queue-path: ./log-queue
    disk-resend-interval: 60000
    max-disk-queue-size-mb: 1024
    compression-enabled: false
    compression-threshold: 1024
    metrics-enabled: true
    metrics-collection-interval: 60000
    expose-metrics-via-jmx: true
    cors-enabled: false
    sensitive-patterns:
      - password
      - card
    sensitive-value-replacement: "***"
    max-retries: 3
    retry-delay: 1000
    use-https: false
    allow-insecure-tls: false
    suppress-connection-errors: true
    max-connection-error-logs-per-period: 1
    connection-error-log-period: 300000
    use-exponential-backoff: true
    initial-backoff-delay: 5000
    max-backoff-delay: 1800000
    connection-check-interval: 300000
    connection-check-timeout: 5000
    http-client-pool-max-total: 100
    http-client-pool-default-max-per-route: 20
    http-client-pool-evict-idle-connections-after: 30
```

---

## 8. 참고/실전 팁

> **로그 압축 관련 주의사항**
> - `compression-enabled: true`를 사용할 경우, Logstash 입력에 반드시 `decompress_request => true` 옵션을 추가해야 합니다. 그렇지 않으면 압축된 로그 데이터가 제대로 처리되지 않습니다.

> **디스크 큐 경로**
> - 운영 환경에서는 기본값(`./log-queue`) 대신 영구적이고 쓰기 가능한 경로로 지정하세요.

> **민감 정보 필터링**
> - `sensitive-patterns`에 지정된 키워드는 로그에서 자동으로 마스킹됩니다.

> **CORS 설정**
> - `cors-enabled: true`로 설정 시 모든 오리진/헤더/메소드 허용(CORS 보안 주의)

> **JMX 지표**
> - JConsole, VisualVM 등으로 `com.cholog.logger:type=LoggingMetrics` MBean을 조회할 수 있습니다.

---

- 전체 옵션은 [샘플 설정](./application.sample.yml) 또는 소스코드를 참고하세요.
- 추가 옵션/고급 설정은 [고급 사용법](./Advanced-Usage.md) 문서 참고 