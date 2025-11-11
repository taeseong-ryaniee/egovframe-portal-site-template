# Dokploy 배포 가이드 (Docker Compose)

이 프로젝트는 Docker Compose 기반으로 Tomcat + MySQL 스택(기본) 또는 Apache + Tomcat + MySQL(옵션)을 배포합니다. Dokploy에서 "Docker Compose App"으로 등록하면 저장소에서 직접 빌드 후 구동할 수 있습니다.

## 1) 저장소 연결
- Dokploy 대시보드 → Create Application → Docker Compose App
- Repository URL: 이 저장소의 Git URL
- Branch/Path: 기본 브랜치, `docker-compose.yml`이 루트에 위치

## 2) 환경 변수 설정
- Compose는 아래 값을 환경 변수로 주입받도록 되어 있습니다.
  - `MYSQL_DATABASE`(default: `pst`)
  - `MYSQL_ROOT_PASSWORD`(default: `rootpw`)
  - `MYSQL_USER`(default: `egov`)
  - `MYSQL_PASSWORD`(default: `egovpw`)
  - `TZ`(default: `Asia/Seoul`)
  - `JAVA_OPTS`(default: `-Xms256m -Xmx1024m -Dfile.encoding=UTF-8`)
- 운영에서는 비밀번호 값을 꼭 변경하세요.

## 3) 볼륨/스토리지 설정
- Compose 네임드 볼륨
  - `mysql_data`: MySQL 데이터 영속화
  - `upload_data`: 첨부파일 저장 경로(`/user/file/sht/`) 영속화
- Dokploy UI에서 해당 볼륨을 Persistent Volume으로 연결하세요.

## 4) 빌드 설정
- `tomcat` 서비스는 Dockerfile로 소스에서 WAR를 빌드합니다.
  - Base: `maven:3.8.8-eclipse-temurin-8` → `tomcat:9.0-jdk8-temurin`
  - 빌드 캐시를 활성화하면 재배포 속도가 개선됩니다.
- `mysql`은 기동 시 자동 스키마/데이터 로드
  - `docker/mysql/10_init.sh`가 Oracle DDL을 MySQL DDL로 변환 후 로드

## 5) 네트워킹/도메인
- 기본 구성: Dokploy Ingress가 `tomcat:8080`으로 라우팅하도록 설정합니다.
- WAR는 ROOT와 `/pst_webapp` 컨텍스트에 동시에 배포되어, 하드코딩된 `/pst_webapp/...` 링크도 정상 동작합니다.
- SSL은 Dokploy에서 도메인/인증서를 설정하세요(예: Let’s Encrypt).

## 6) 배포
- Deploy 실행 → 첫 배포는 의존성 다운로드/DB 초기화로 수 분 소요될 수 있습니다.
- 상태 확인
  - `mysql` 로그: 스키마/데이터 로드 확인
  - `tomcat` 로그: WAR 배포 및 기동 확인

## 7) 접속/계정
- `http://<도메인>/` 또는 `http://<도메인>/pst_webapp/`
- 관리자: `admin / 1`, 사용자: `user1 / 1`

## 8) 운영 권장사항
- 보안: 환경 변수(특히 DB 비밀번호) 변경, 3306 외부 노출 금지
- 성능: `JAVA_OPTS` 및 커넥션 풀 튜닝, MySQL 리소스 제한 설정
- 백업: `mysql_data`, `upload_data` 주기적 백업
- 모니터링: Dokploy 헬스체크/알람 활성화
