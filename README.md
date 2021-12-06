# url-shorting-service
url-shorting-service

리눅스 서버(CentOS 7 기준)

## 1. 자바 설치

- OpenJDK 버전 확인
$ yum list java*jdk-devel

- Java8 설치
$ yum install -y java-11-openjdk-devel.x86_64

- Java 버전 확인
$ javac -version
$ java -version

## 2. Gradle 설치

- Gradle 다운로드
$ wget https://services.gradle.org/distributions/gradle-7.3.1-bin.zip -P /tmp

- 압축 풀기
$ sudo unzip -d /opt/gradle /tmp/gradle-7.3.1-bin.zip

- 환경변수 설정
$ sudo nano /etc/profile.d/gradle.sh
export GRADLE_HOME=/opt/gradle/gradle-7.3.1
export PATH=${GRADLE_HOME}/bin:${PATH}

- 쉘 파일 권한 변경
$ sudo chmod +x /etc/profile.d/gradle.sh

- 환경 변수 로딩
$ source /etc/profile.d/gradle.sh

## 3. Nginx 설치

- Nginx 저장소 추가
$ vi /etc/yum.repos.d/nginx.repo
[nginx]
name=nginx repo
baseurl=http://nginx.org/packages/centos/7/$basearch/
gpgcheck=0
enabled=1

- Nginx 설치
$ yum install -y nginx

## 4. node.js & npm & vue 설치

- 저장소 추가
$ curl -sL https://rpm.nodesource.com/setup_10.x | sudo bash -

- node.js 설치
$ sudo yum install nodejs

- vue 설치
$ npm install -g @vue/cli
$ vue add @vue/cli-service

## 5. Shortening Url Service API 설치

- 깃 클론
$ git clone https://github.com/WonHyeongCho/url-shorting-service.git

- Gradle Build
$ {설치경로}/url-shortening-service-api/gradlew build

- 자바 실행
$ nohup java -jar {설치경로}/url-shortening-service-api/build/libs/url-shorting-service-api-0.0.1-SNAPSHOT.jar > url-shorting-service-api.log 2>&1 &

6. Shortening Url Service WEB 설치

- vue 
