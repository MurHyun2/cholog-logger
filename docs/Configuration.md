# 설정 옵션 (Configuration)

**cholog-logger v1.0.2** 기준 전체 설정 옵션 안내입니다.

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
| **compress-logs** | 로그 압축 활성화 여부 | false | true |

> **주의:**<br/>
> - 운영 환경에서는 `disk-queue-path`를 반드시 영구적이고 쓰기 가능한 경로로 지정하세요.<br/>
> - `compress-logs: true` 사용 시 Logstash 입력에 `decompress_request => true` 옵션 필요!
> - 기존 `compression-enabled`와 `compression-threshold` 옵션은 v1.0.1부터 `compress-logs`로 통합되었습니다.

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
    compress-logs: false
    metrics-enabled: true
    metrics-collection-interval: 60000
    expose-metrics-via-jmx: true
    cors-enabled: false
    sensitive-patterns:
      # 개인정보 필터링
      - mdcContext.request_param_password
      - mdcContext.request_param_ssn
      - mdcContext.request_param_cardNumber
      - mdcContext.request_param_phoneNumber
      - mdcContext.request_param_email
      
      # 인증정보 필터링
      - headers.authorization
      - headers.cookie
      - mdcContext.request_header_x-api-key
      
      # 시스템 정보 필터링
      - performanceMetrics.cpuUsage
      - performanceMetrics.memoryUsage
      - timestamp
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
> - `compress-logs: true`를 사용할 경우, Logstash 입력에 반드시 `decompress_request => true` 옵션을 추가해야 합니다. 그렇지 않으면 압축된 로그 데이터가 제대로 처리되지 않습니다.

> **디스크 큐 경로**
> - 운영 환경에서는 기본값(`./log-queue`) 대신 영구적이고 쓰기 가능한 경로로 지정하세요.

> **민감 정보 필터링**
> - `sensitive-patterns`에 지정된 키워드는 로그에서 자동으로 마스킹됩니다.
> - `sensitive-value-replacement`로 마스킹 문자열을 변경할 수 있습니다(기본값: "***").
> - v1.0.2부터 모든 필터에서 민감정보 필터링이 일관되게 적용됩니다.
> - **중요:** v1.0.2부터 `RequestTimingFilter`에서도 민감 파라미터 필터링이 적용됩니다.
> - **주의:** 부분 문자열 매칭 사용 - `card`를 지정하면 `cardHolder`, `discardReason` 등 'card'가 포함된 모든 필드가 필터링될 수 있습니다.
> - **권장사항:** 의도하지 않은 필터링을 방지하려면 경로를 명확하게 지정하세요. 예: `mdcContext.request_param_age`
> - **기본 필터링:** 다음 헤더와 파라미터는 설정과 무관하게 항상 필터링됩니다:
>   - 헤더: authorization, auth, cookie, jwt, token, password, secret, credential, key, x-api-key, api-key, apikey, access, private
>   - 파라미터: password, pwd, secret, token, auth, key, apikey, api-key, credential, card, credit, cvv, cvc, pin, ssn, social, sin, tax, fiscal, passport, license, national, identity, private
> - 경로를 명확하게 지정하여 의도하지 않은 필터링을 방지하세요. 예:
>   ```yaml
>   sensitive-patterns:
>     # 인증 및 개인정보 필터링
>     - headers.authorization              # 인증 헤더 필터링
>     - headers.cookie                     # 쿠키 필터링
>     - mdcContext.request_param_password  # 비밀번호 파라미터
>     - mdcContext.request_param_ssn       # 주민번호 파라미터  
>     - mdcContext.request_param_phone     # 전화번호 파라미터
>     
>     # 성능 및 시스템 정보 필터링
>     - performanceMetrics.cpuUsage        # CPU 사용량 숨김
>     - performanceMetrics.memoryUsage     # 메모리 사용량 숨김
>     
>     # 민감한 응답 데이터 필터링
>     - response_body                      # 전체 응답 본문 필터링(필요시)
>   ```
> - 특정 타입의 모든 필드를 필터링하려면 접두어 사용: `request_param_` 또는 `request_header_`

> **CORS 설정**
> - `cors-enabled: true`로 설정 시 모든 오리진/헤더/메소드 허용(CORS 보안 주의)

> **JMX 지표**
> - JConsole, VisualVM 등으로 `com.cholog.logger:type=LoggingMetrics` MBean을 조회할 수 있습니다.

---

- 전체 옵션은 [샘플 설정](./application.sample.yml) 또는 소스코드를 참고하세요.
- 추가 옵션/고급 설정은 [고급 사용법](./Advanced-Usage.md) 문서 참고 