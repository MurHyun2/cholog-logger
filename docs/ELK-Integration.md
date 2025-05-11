# ELK 연동 가이드 (ELK Integration)

cholog-logger는 ELK 스택(Logstash, Elasticsearch, Kibana)과 쉽게 연동할 수 있습니다.

## Logstash 입력 설정 예시

```conf
input {
  http {
    port => 5000
    codec => json
    decompress_request => true # 압축 로그 지원
  }
}

filter {
  date {
    match => [ "timestamp", "ISO8601" ]
  }
}

output {
  elasticsearch {
    hosts => ["http://your-elasticsearch-host:9200"]
    index => "cholog-%{service_name}-%{+YYYY.MM.dd}"
  }
}
```

## Kibana에서 인덱스 패턴 생성
- `cholog-*` 인덱스 패턴을 추가해 로그를 검색/시각화할 수 있습니다.

## 참고
- 로그 압축(`compression-enabled: true`)을 사용할 경우 Logstash 입력에 `decompress_request => true` 옵션이 필요합니다. 