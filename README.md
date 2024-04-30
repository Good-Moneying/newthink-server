## System Architecture

## Multi Module

## Tech Stack

## ERD
![ERD.png](docs%2FERD.png)

## Convention
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
*Naming Convention*

> 코드를 작성한 의도와 목적을 명확하게 드러냅니다. 
- **Variables** : 축약어를 사용하지 않습니다. 변수 명에 자료구조를 포함하지 않습니다. (e.g., `CarList.java` ❌) <br>
- **Methods**: `lowerCamelCase`를 사용합니다. 메소드 명은 동사 및 전치사로 시작합니다. (e.g., `getUserInfo()`) <br>
- **Database Columns**: `snake_case`를 사용합니다. (e.g., `member_id`) <br>
- **End Points**: REST API를 준수합니다. (e.g., `GET` www.example.com/users/1) <br>

## Co-Work Flow
1. Issue를 생성합니다.
2. Issue 번호로 시작하는 브랜치를 판 후 개발을 시작합니다. 
3. 개발 완료 후 Pull Request를 생성합니다.
4. Reviewer는 Code Review를 진행합니다.
5. Code Review를 바탕으로 개발자는 수정을 진행합니다.
6. 최종 확인 후 Reviewer가 Merge를 진행합니다.

## Git Flow 
![git_flow.png](docs%2Fgit_flow.png)

### Branch Naming Convention
- main 
- develop
- feature/Issue#-feature
- hotfix/Issue#-feature
- refactor/Issue#-feature

