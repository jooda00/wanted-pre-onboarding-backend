# wanted-pre-onboarding-backend
프리온보딩 백엔드 인턴십 선발과제

> 언어 및 프레임워크 : Java 17 & Spring Boot 3.3.2
> 
> RDBMS : MySql

### 📌 서비스 개요
기업의 채용을 위한 웹 서비스. 
회사는 채용공고를 생성하고, 이에 사용자는 지원한다.

### 📌 요구사항 및 API 명세
#### 공통 응답
요청이 정상적으로 처리된 경우

(POST/PUT은 "CREATED", GET/DELETE는 "OK")

(밑의 응답 값은 data에 들어가는 json만 삽입했다.)
```json
{
  "code": "응답 코드"
  "data": {"json 객체"}
}
```
#### 1. 채용 공고 등록
(회사는 미리 DB에 저장을 해놓았다고 가정한다.)
```json
{
  "companyId" : 1,
  "position" : "백엔드 주니어 개발자",
  "compensation" : 1000000,
  "content" : "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "skill" : "Python"
}
```

#### 2. 채용 공고 수정
회사 id와 채용공고 id를 PathVariable로 받아서 채용공고를 수정한다.
```json
{
  "position" : "프론트엔드 주니어 개발자", # 변경
  "compensation" : 1500000,
  "content" : "원티드랩에서 프론트엔드 주니어 개발자를 채용합니다. 자격요건은..", # 변경
  "skill" : "Python"
}
```

#### 3. 채용 공고 삭제
회사 id와 채용공고 id를 PathVariable로 받아서 채용공고를 삭제한다.

#### 4. 채용 공고 목록 조회
```json
 [
    {
      "recruitmentId": 1,
      "companyName": "wanted",
      "country": "korea",
      "city": "seoul",
      "position": "백엔드 개발자",
      "compensation": 1000000,
      "skill": "Java, Spring"
    },
    {
      "recruitmentId": 2,
      "companyName": "wanted",
      "country": "korea",
      "city": "seoul",
      "position": "프론트엔드 개발자",
      "compensation": 1000000,
      "skill": "JS, React"
    },
    {
      "recruitmentId": 3,
      "companyName": "삼성전자",
      "country": "한국",
      "city": "부산",
      "position": "디자이너, 개발자",
      "compensation": 1000000,
      "skill": "Figma"
    }
]
```

#### 5. 채용 공고 검색
keyword를 받아서 해당 keyword를 가진 채용공고 목록을 반환한다.

`search=백엔드`
```json
[
    {
      "recruitmentId": 1,
      "companyName": "wanted",
      "country": "korea",
      "city": "seoul",
      "position": "백엔드 개발자",
      "compensation": 1000000,
      "skill": "Java, Spring"
    }
]
```

#### 6. 채용 공고 상세 페이지
채용공고 id를 PathVariable로 받아서 채용공고 상세 페이지를 반환한다.

`채용내용 + 회사가 올린 다른 채용공고 id List` 추가 반환
```json
{
    "recruitmentId": 1,
    "companyName": "wanted",
    "country": "korea",
    "city": "seoul",
    "position": "백엔드 개발자",
    "compensation": 1000000,
    "skill": "Java, Spring",
    "content": "백엔드 개발자 구합니다. 과제테스트 포함입니다.",
    "otherRecruitmentsIds": [
      1,
      2,
      5
    ]
}
```

#### 7. 사용자 채용 공고 지원
사용자 id와 채용공고 id를 PathVariable로 받아서 사용자가 채용 공고에 지원할 수 있도록 한다.
```json
{
  "userId": 1,
  "recruitmentId": 1
}
```

### 📌 Git Convention
```
- feat: 새로운 기능
- chore: 빌드 업무 수정, 패키지 매니저 수정. 기능과 상관 없는 파일
- refactor: 기능에 영향을 주지 않는 코드 리팩토링
- test: 테스트 코드
```
