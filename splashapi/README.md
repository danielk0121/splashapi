# 개요
- 카카오 페이 입사 지원 과제 프로젝트
- **뿌리기 서비스 API 서버 구현**
- 과제 요구사항 pdf: [링크](docs/카카오페이공채 server 개발과제.pdf)
- 서비스 요구사항 요약: [링크](docs/서비스 요구사항 요약.txt)

# 개발환경
- spring-boot 2.3.1
- embeded-tomcat
- maven
- mysql
- mybatis
- java 11
- database 테이블 구성은 create sql 파일 참고
- [splash 테이블 create sql 파일](docs/dbschema/splash_create.sql)
- [splash_receive 테이블 create sql 파일](docs/dbschema/splash_receive_create.sql)

# 특이사항 - 1. 뿌리기 배분 로직
- 문제 pdf에서 언급된 내용 : 

```
뿌릴 금액을 인원수에 맞게 분배하여 저장합니다. (분배 로직은 자유롭게 구현해 주세요.)
```

- 적용한 분배 로직 : 

```
RATE1: 0.7
RATE2: 0.3

뿌리기 금액을 RATE1:RATE2 으로 나누고, RATE1는 균등 분배, RATE2은 균등 분배 * 랜덤 배율 적용
랜덤 배율 적용으로 인해 남는 금액이 발생하고,
남은 금액은 랜덤으로 1명을 선택해서 올인하여, 
1명이 눈에 띄게 많이 받을 수 있도록 함
```

# 특이사항 - 2. 뿌리기 토큰
- 문제 pdf 에서 언급된 내용 : 

```
"token은 3자리 문자열로 구성되며 예측이 불가능해야 합니다."
```

- token string 길이가 3글자 인지, 문자열 3개로 구성되는건지 해석이 모호함
- string 길이 3글자로 구현함, ex, Fj2
- 이로인해 뿌리기 건의 유니크id 가 되어야 할 토큰의 경우의 수가 너무 적음 (대략 20만건)
- 따라서, **[뿌리기 생성자 id + roomId + token]** 3개 조합을 의미상 고유키로 취급하도록 구성함

# 특이사항 - 3. 미구현 부분 (혹은 TODO 리스트)
- 아래 사항들은 테스트 프로젝트 이므로 생략하였다 (시간 부족 이유도...)
- swagger-ui
- 파일 로깅 및 롤링
- 데이터 베이스 접근 부분에 대한 로컬 캐시
- 각 API에 대한 required parameter 유효성 검사
- PK를 제외한 추가 인덱스
- 성능 테스트 및 테스트 결과
- DataSource 설정 튜닝

# 특이사항 - 4. 서버 타임존
- 데이터베이스 서버, API 서버 모두 Asia/Seoul 타임존을 사용한다고 가정한다
- 다만, 코드상에서 타임존을 강제하는 부분은 특별히 없으므로, jdbc-url 과 데이터베이스 서버 타임존을 변경하는 작업이면 충분하다
