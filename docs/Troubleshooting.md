# 문제 해결 (Troubleshooting)

## 로그가 전송되지 않음
- `cholog.logger.url` 설정이 올바른지 확인
- 네트워크 연결 및 중앙 로그 서버 상태 확인
- 애플리케이션 로그에서 에러 메시지 확인

## HTTP 요청/응답 본문이 로깅되지 않음
- `cholog.logger.request-body-logging`이 true인지 확인
- Content-Type이 로깅 대상인지 확인

## 민감 정보가 필터링되지 않음
- `cholog.logger.sensitive-patterns` 설정 확인
- v1.0.2부터는 `RequestTimingFilter`에서도 민감 파라미터 필터링이 적용됩니다

## 의도하지 않은 필드가 필터링됨
- 부분 문자열 매칭으로 의도하지 않은 필드가 필터링되고 있을 수 있습니다
- 경로를 정확하게 지정하여 필터링을 제한하세요 (예: `mdcContext.request_param_age`)

## 디스크 큐 저장 오류
- 디스크 큐 경로의 쓰기 권한 확인
- 최대 디스크 큐 크기 설정 확인

## 압축 로그가 ELK에서 처리되지 않음
- Logstash 입력에 `decompress_request => true` 옵션 추가

## 기타
- 추가적인 문제는 [Issues](https://github.com/MurHyun2/cholog-logger/issues)에 등록해 주세요. 