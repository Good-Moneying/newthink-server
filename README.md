##  서비스명
![duduk.png](docs%2Fduduk.png)

## 서비스 소개
### 경제 뉴스레터 서비스, 두둑 (Do-duk : *Do Develop Ur Knowledge!*)

> 두둑(Do-Duk)은 여러분의 머리와 지갑을 두둑-히 채워주는 경제 뉴스레터 서비스입니다.

![service_target.png](docs%2Fservice_target.png)

## GUI
![gui.png](docs%2Fgui.png)

## System Architecture
![system_architecture.png](docs%2Fsystem_architecture.png)
## Hexagonal Multi Module
![multi-module.png](docs%2Fmulti-module.png)

#### api-server
- 프론트와 통신을 담당하는 API 모듈입니다.
- 외부 요청을 받아 내부 서비스로 전달하고, 내부 서비스의 응답을 외부로 전달합니다.
- Security 등과 같은 Infrasturcture 코드를 포함하고 있습니다.

#### batch
- 주기적으로 실행되는 작업을 담당하는 모듈로, 스케줄러를 통해 주기적으로 실행되는 작업을 수행합니다.
- 크롤링, 데이터 처리, 데이터 저장 등의 작업을 수행합니다.

#### application
- 내부 비즈니스 로직을 담당하고 있는 모듈로 헥사고날 아키텍쳐의 Port와 Adapter 코드가 작성되어 있습니다.
- 외부 요청을 받아 비즈니스 로직을 수행하고, 결과를 반환합니다.

#### domain
- 오로지 POJO 객체만을 가지고 있는 모듈입니다.
- 도메인 클래스와 도메인 서비스 클래스가 정의될 수 있습니다.
- 도메인 서비스에는 ‘트랜잭션의 단위’를 정의하며, ‘요청 데이터를 검증’, ‘이벤트를 발생’하는 일 등의 도메인의 비지니스를 작성합니다.

#### core
- 공통적으로 사용되는 코드를 담당하는 모듈입니다.
- 시스템 내 모든 모듈들이 의존할 수 있을만큼 얇은 의존성을 제공합니다.
- 가능한 사용하지 않습니다.

## 📜 ERD
![ERD.png](docs%2FERD.png)

## ⚒️ Tech Stack
### Front-end
![Android Studio](https://img.shields.io/badge/Android%20Studio-%233DDC84?logo=androidstudio&logoColor=white)
![Flutter](https://img.shields.io/badge/Flutter-02569B?style=for-the-badge&logo=flutter&logoColor=white)
![Dart](https://img.shields.io/badge/Dart-0175C2?style=for-the-badge&logo=dart&logoColor=white) <br>

### Back-end
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-%6DB33F?logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-%6DB33F?logo=springsecurity&logoColor=white)
![Spring JPA](https://img.shields.io/badge/Spring%20JPA-%6DB33F?logo=&logoColor=white)
![Spring Batch](https://img.shields.io/badge/Spring%20Batch-%6DB33F?logo=&logoColor=white) <br>
![Amazon EC2](https://img.shields.io/badge/Amazon%20EC2-FF9900?logo=amazonec2&logoColor=white)
![Amazon RDS](https://img.shields.io/badge/Amazon%20RDS-527FFF?logo=amazonRDS&logoColor=white)
![Amazon S3](https://img.shields.io/badge/Amazon%20S3-FC390E?logo=amazons3&logoColor=white) <br>
![Github Actions](https://img.shields.io/badge/Github%20Actions-2088FF?logo=githubactions&logoColor=white) ![Swagger](https://img.shields.io/badge/Swagger-85EA2D?logo=swagger&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-43B02A?logo=selenium&logoColor=white) <br>
![QueryDSL](https://img.shields.io/badge/QueryDSL-00465B?logo=&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-512BD4?logo=&logoColor=white) <br>

### Co-Work Tool
![Jira](https://img.shields.io/badge/Jira-0052CC?logo=jira&logoColor=white)
![Github](https://img.shields.io/badge/Github-181717?logo=github&logoColor=white)
![Slack](https://img.shields.io/badge/Slack-4A154B?logo=slack&logoColor=white)
![figma](https://img.shields.io/badge/Figma-F24E1E?logo=figma&logoColor=white)
![notion](https://img.shields.io/badge/Notion-000000?logo=notion&logoColor=white)

## 💡 기술 스택 선정 이유
### Front-end
- **Android Studio**
  - 안드로이드 어플 개발의 생산성과 안정성을 높일 수 있는 통합 개발 환경을 제공합니다.

- **Flutter**
  - 단일 코드베이스로 안드로이드와 iOS 앱을 개발할 수 있으며, 빠르고 화려한 UI를 구축할 수 있는 높은 생산성과 확장성을 제공합니다.

- **Dart**
  - 플러터와의 통합으로, 강력한 성능과 생산성을 제공합니다.

- **MVVM**
  - Model-View-ViewModel로 구성되어, UI와 데이터 처리 로직을 분리하여 코드의 유지보수성을 높이는 아키텍처 패턴입니다.

- **GetX**
  - 상태 관리 및 라우팅, 종속성, 테마 등을 처리하기 위한 다양한 기법을 제공하는 라이브러리입니다.

- **http & dio**
  - 네트워크 통신 및 데이터베이스 접근을 위한 라이브러리입니다.

### Back-end
- **Spring Boot**
  - 스프링 프레임워크를 기반으로 자동 설정과 스타터 종속성을 제공하여 웹 애플리케이션 개발과 배포를 신속하게 수행할 수 있도록 돕습니다.

- **JPA (Java Persistence API)**
  - SQL 작성 없이 객체 지향적 방식으로 데이터베이스와 상호작용할 수 있게 해주는 자바의 표준 ORM 기술입니다.

- **Querydsl**
  - 타입 안전을 보장하는 프레임워크를 통해 복잡한 쿼리를 쉽게 작성하고 컴파일 시점에서 오류를 잡을 수 있습니다.

- **AWS RDS (MySQL)**
  - 서버 유지보수나 패치 관리 없이 MySQL 데이터베이스를 운영할 수 있는 AWS의 관리형 서비스입니다.

- **AWS S3 Storage**
  - 높은 가용성과 보안을 제공하는 객체 스토리지 서비스로, 어떠한 양의 데이터도 저장하고 검색할 수 있습니다.

- **Java 17**
  - 장기적인 안정성과 보안 업데이트를 제공하는 최신 Long-Term Support (LTS) 버전의 자바입니다.

- **Spring Batch**
  - 대용량 데이터 처리를 위한 배치 처리 프레임워크로, 스케줄링, 트랜잭션 관리, 재시도, 로깅 등을 지원합니다.
  - **Scheduler** (Spring Scheduler, Quartz) 와 연동하여 주기적으로 증권 정보 혹은 뉴스를 크롤링 할 때 사용합니다.

- **Spring Security**
  - 스프링 기반 애플리케이션의 보안을 강화하며, 인증과 권한 부여, CSRF 방어 등을 포함한 종합적인 보안 솔루션을 제공합니다.

- **JWT (JSON Web Tokens)**
  - 서버와 클라이언트 간의 정보를 토큰 형태로 간소화하며, 상태를 저장하지 않는 서비스에 적합한 인증 시스템입니다.

- **GitHub Actions**
  - 코드 통합, 테스트, 배포를 자동화하여 개발 워크플로우의 효율성을 향상시키는 CI/CD 도구입니다.

- **Swagger**
  - RESTful API의 설계, 빌드, 문서화를 지원하며 프론트와 백엔드 개발자 간의 협업과 API 이해를 증진시키는 소프트웨어 프레임워크입니다.

- **Selenium**
  - 동적 크롤링을 위한 웹 애플리케이션 라이브러리입니다.

- **Spring Scheduler**
  - 스프링 프레임워크 내에서 주기적인 작업을 쉽게 설정하고 관리할 수 있게 해주는 스케줄링 기능입니다.

- **Jsoup**
  - HTML에서 데이터를 파싱하고 조작하는 자바 라이브러리로, 정적 웹 크롤링 및 데이터 추출 작업에 유용합니다.

- **Naver Clova Summary**
  - 네이버의 자연어 처리 기술을 사용하여 대량의 텍스트를 자동으로 요약해 주는 도구로, 정보 파악과 의사결정에 효과적입니다.
  - 크롤링한 뉴스 기사를 요약하여 사용자에게 제공합니다.

## 📌 Convention
### Naming Convention
- 파일 : CamelCase + SnakeCase
- 클래스명 : PascalCase
- 함수/변수명 : CamelCase

### Branch Naming Convention
- main
- develop
- feature/Issue#-feature
- hotfix/Issue#-feature
- refactor/Issue#-feature

### Commit Convention

| Tag      | Description                                         |
|----------|-----------------------------------------------------|
| `feat`   | Commits that add a new feature.                     |
| `fix`    | Commits that fix a bug.                             |
| `hotfix` | Fix an urgent bug in issue or QA.                   |
| `build`  | Commits that affect build components.               |
| `chore`  | Miscellaneous commits.                              |
| `style`  | Commits for code styling or format.                 |
| `docs`   | Commits that affect documentation only.             |
| `test`   | Commits that add missing tests or correcting existing tests. |
| `refactor`| Commits for code refactoring.                      |

### Coding Convention
> 코드를 작성한 의도와 목적을 명확하게 드러냅니다.
- **Variables** : 축약어를 사용하지 않습니다. 변수 명에 자료구조를 포함하지 않습니다. (e.g., `CarList.java` ❌) <br>
- **Methods**: `lowerCamelCase`를 사용합니다. 메소드 명은 동사 및 전치사로 시작합니다. (e.g., `getUserInfo()`) <br>
- **Database Columns**: `snake_case`를 사용합니다. (e.g., `member_id`) <br>
- **End Points**: REST API를 준수합니다. (e.g., `GET` www.example.com/users/1)

## 📍 Co-Work Flow
1. Issue를 생성합니다.
2. Issue 번호로 시작하는 브랜치를 판 후 개발을 시작합니다.
3. 개발 완료 후 Pull Request를 생성합니다.
4. Reviewer는 Code Review를 진행합니다.
5. Code Review를 바탕으로 개발자는 수정을 진행합니다.
6. 최종 확인 후 Reviewer가 Merge를 진행합니다.

## 🐬 Git Flow
![git_flow.png](docs%2Fgit_flow.png)