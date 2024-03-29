# GiftManagement
### Knu dbP project
## 데이터베이스 프로그래밍 프로젝트

- 프로젝트 설명 PPT: https://drive.google.com/file/d/1uFDKwI3tQUQMHAf6cIE4Em960Ye_E-9A/view?usp=sharing
- 작업 스프레드시트: https://docs.google.com/spreadsheets/d/1etbfkTbC5fK2ZxU7mUW5zJ4jBd48RMnKlROYoGpQviI/edit?usp=sharing
- ERD CLOUD: https://www.erdcloud.com/d/Jj2eCvvraJPYXo8Yv
- 프로젝트 실행 관련 SQL : https://drive.google.com/file/d/1243uI3f6OpKwam60LAttEnEpbsWHbb9P/view?usp=sharing

## 기술 스택 
- FrontEnd: Thymleaf, javascript
- BackEnd: SpringBoot
- DataBase: mariaDB

<br>

## 규칙
- 1 작업 1 커밋
- 각각의 수정된 코드 주석 작성 및 수정 일자, 수정자 이름 작성
- 작업 후 스프레드 시트 수정 필수

## commit 메시지 참고사항
- FEAT : 새로운 기능의 추가
- FIX: 버그 수정
- DOCS: 문서 수정
- STYLE: 스타일 관련 기능(코드 포맷팅, 세미콜론 누락, 코드 자체의 변경이 없는 경우)
- REFACTOR: 코드 리펙토링
- TEST: 테스트 코트, 리펙토링 테스트 코드 추가
- CHORE: 빌드 업무 수정, 패키지 매니저 수정(ex .gitignore 수정 같은 경우)
  <br>

## :exclamation: 주의사항
- main branch push 금지
- 각자 branch 위치만 사용
- <b>작업 후 공용 스프레드 시트 수정</b>
```
https://docs.google.com/spreadsheets/d/1etbfkTbC5fK2ZxU7mUW5zJ4jBd48RMnKlROYoGpQviI/edit#gid=289789655
```
- merge 절대 금지
- compile error 상태로 commit 금지
- .gitignore 설정
```
https://www.toptal.com/developers/gitignore
```

<br>

## :star: git 명령어

- <b>clone : github → local</b>
``` 
git clone https://github.com/delay-100/GiftManagement.git
```
- <b>branch 이름 확인
```
git branch
```
- <b>branch 이동
```
git branch -M <branch 이름>
```
- <b>동기화 : github → local</b>
```
git pull
```
- <b>push : local → github</b>
```
git add .
```
```
git commit -m "메시지"
```
```
git push
```
