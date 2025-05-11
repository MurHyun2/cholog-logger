# cholog-logger

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![JitPack](https://jitpack.io/v/MurHyun2/cholog-logger.svg)](https://jitpack.io/#MurHyun2/cholog-logger)

Spring Boot 애플리케이션을 위한 중앙 집중형 로깅 SDK입니다.  
HTTP 요청/응답, 애플리케이션 로그, 예외 등을 자동으로 캡처하여 중앙 로그 서버(ELK 등)로 전송합니다.

---

## 주요 특징

- HTTP 요청/응답, 예외, 애플리케이션 로그 자동 수집
- 중앙 로그 서버(ELK 등)로 비동기/배치 전송
- 민감 정보 자동 필터링, GZIP 압축 지원
- Spring Boot 자동 설정(AutoConfiguration)
- 디스크 큐, JMX 상태 모니터링 등 고급 기능

---

## 설치 방법

### Gradle 사용 시

```gradle
dependencies {
    implementation 'com.github.MurHyun2:cholog-logger:v1.0.0'
}
```

### Maven 사용 시

```xml
<dependency>
  <groupId>com.github.MurHyun2</groupId>
  <artifactId>cholog-logger</artifactId>
  <version>v1.0.0</version>
</dependency>
```

---

## 빠른 시작 및 설정 예시

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

## 간단한 사용 예제

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

## 문서

- [설정 옵션 전체 보기](docs/Configuration.md)
- [고급 사용법](docs/Advanced-Usage.md)
- [ELK 연동 가이드](docs/ELK-Integration.md)
- [문제 해결](docs/Troubleshooting.md)
- [FAQ](docs/FAQ.md)
- [샘플 설정 전체 보기](docs/application.sample.yml)
- [CHANGELOG](CHANGELOG.md)

---

## 기여 및 문의

- 이슈/PR 환영: [Issues](https://github.com/MurHyun2/cholog-logger/issues)
- Author: [Daehyun Lee (MurHyun2)](https://github.com/MurHyun2)
- Email: eddy152264@gmail.com

---

## 라이선스

이 프로젝트는 [MIT License](LICENSE)로 배포됩니다.
