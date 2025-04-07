# 집에 잠든 개인용기로 동네를 깨우다🌱 Packit
> 이 프로젝트는 [PackItProject/PACK_IT_Server](https://github.com/PackItProject/PACK_IT_Server) 를 기반으로 개발되었으며, 기존 프로젝트를 리팩토링하고 CI/CD 및 테스트 환경을 고도화한 **Dev 확장판**입니다.

## Tech Stack
### Backend
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/spring security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"> <img src="https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens"> <img src="https://img.shields.io/badge/spring data jpa-6DB33F?style=for-the-badge&logoColor=white"> <img src="https://img.shields.io/badge/querydsl-6DB33F?style=for-the-badge&logoColor=white"> <img src="https://img.shields.io/badge/hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white"> <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">

### DB
<img src="https://img.shields.io/badge/amazon rds-527FFF?style=for-the-badge&logo=amazonrds&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/jasypt-0769AD?style=for-the-badge&logoColor=white"> <img src="https://img.shields.io/badge/amazon s3-569A31?style=for-the-badge&logo=amazons3&logoColor=white">

### CI/CD
<img src="https://img.shields.io/badge/github actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white"> <img src="https://img.shields.io/badge/beanstalk-FF9900?style=for-the-badge&logo=awslambda&logoColor=white">

### Deploy
<img src="https://img.shields.io/badge/amazon ec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white"> <img src="https://img.shields.io/badge/nginx-009639?style=for-the-badge&logo=nginx&logoColor=white"> <img src="https://img.shields.io/badge/elastic beanstalk-FF9900?style=for-the-badge&logo=awslambda&logoColor=white">

### Develop Tool
<img src="https://img.shields.io/badge/intelliJ-000000?style=for-the-badge&logo=intellijidea&logoColor=white"> <img src="https://img.shields.io/badge/postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/swagger-6DB33F?style=for-the-badge&logo=swagger&logoColor=white">
<br><br>

## Commit/PR Convention
**Commit**
```
#1 feat: 장바구니 담기 테스트 코드 작성
```
- #이슈번호 타입: 커밋 설명
<br>

**Pull Request**
```
[feature/1-cart-test] 장바구니 테스트 작성
```
- [브랜치명] 설명
<br>

## Branch Strategy
- main
    - 실제 운영용 배포 이력 관리
- develop
    - feature 브랜치 통합, 테스트 후 main으로 병합
- feature
    - 기능 단위 개발 브랜치
- test
    - 테스트 전용 분기
- fix
    - hotfix나 버그 수정 브랜치
<br>

## Member
|[양가연](https://github.com/gayeon7877)|
|:---:|
|<img src="https://github.com/gayeon7877.png" width="180" height="180">|
|**CI/CD & <br> Backend Developer & <br> Architect**|
