# url-shorting-service
url-shorting-service

리눅스 서버(CentOS 7 기준)

1. 자바 설치

- OpenJDK 버전 확인
$ yum list java*jdk-devel

- Java8 설치
$ yum install -y java-11-openjdk-devel.x86_64

- Java 버전 확인
$ javac -version
$ java -version

2. Gradle 설치

- Gradle 다운로드
$ wget https://services.gradle.org/distributions/gradle-5.0-bin.zip -P /tmp

- 압축 풀기
$ sudo unzip -d /opt/gradle /tmp/gradle-5.0-bin.zip

- 환경변수 설정
$ sudo nano /etc/profile.d/gradle.sh

- 쉘 파일 권한 변경
$ sudo chmod +x /etc/profile.d/gradle.sh

- 환경 변수 로딩
$ source /etc/profile.d/gradle.sh


2. Shortening Url Service 설치

- 깃 클론
$ git clone https://github.com/WonHyeongCho/url-shorting-service.git

- Gradle Build
$ ${설치경로}/url-shortening-service-api/gradlew build

