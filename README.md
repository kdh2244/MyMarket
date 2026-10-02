# MyMarket

### 프로젝트 개요
  spring boot 와 jpa 를 이용해서
  키워드 와 위치 기반으로 상품을 목록을 검색하고
  거래하는 중고 거래 프로그램이다.

### 프로젝트 구조 
- 아키텍쳐
<img width="985" height="615" alt="my" src="https://github.com/user-attachments/assets/aecccad9-f8e3-439d-89d3-39a9e35baea9" />
  
- uml 다이어그램
<img width="904" height="523" alt="스크린샷(1228)" src="https://github.com/user-attachments/assets/5ce16fe0-675c-43c1-9b57-1dfe81724dc6" />

### 주요 기술들
1. AWS S3 이미지 저장.
- 클라이언트가 이미지 저장을 서버에 요청하면 서버에서 AWS 에 저장할 수 있는 url 과 저장한 이미지 접근할 수 있는 url을 전달하면
  클라이언트에서 aws에서 이미지를 저장하고 접근 url을 서버에 저장해 달라고 요청한다.
2. db에서 거리 데이터를 point 객체로 저장.
- 사용자와 상품 위치 계산을 위해 db에서 point 객체를 사용해서 위치를 저장하기 때문에 모든 데이터에 대해 거리 계산을 일일이 하면 생기는
 db자원을 많이 사용하고 소요 시간이 길어지는 문제를 해결할 수 있다. 
### 주요 REST API
### 핵심 코드 발췌
