# Docker 이미지의 기반이 되는 베이스 이미지를 지정
FROM eclipse-temurin:17

# 빌드 시점에 Docker 이미지 안으로 전달될 변수를 정의
ARG JAR_FILE=build/libs/api-server.jar

# `ARG`로 지정된 경로에서 JAR 파일을 Docker 이미지 내의 `api-server.jar`로 복사. 이렇게 함으로써 컨테이너가 실행될 때 필요한 실행 파일이 이미지 내에 포함
COPY ${JAR_FILE} api-server.jar

# 컨테이너가 시작될 때 실행될 명령어를 설정
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod,file-logging","-Duser.timezone=UTC","/api-server.jar"]