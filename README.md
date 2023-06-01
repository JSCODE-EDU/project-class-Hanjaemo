# project-class-Hanjaemo
> JSCODE 백엔드 입문 프로젝트 클래스에서 진행한 게시판 서비스

## Description
댓글 없이 제목과 내용만 작성 가능한 게시판 서비스입니다.

간단한 CRUD API부터 회원가입, 로그인 기능까지 구현했습니다.

## Main Feature
### 게시글 작성, 조회, 수정, 삭제 기능
- CRUD 구현

### 생성 시간
- `@CreatedDated`를 활용해 만든 BaseTimeEntity를 상속

### 정렬 및 검색 기능
- JPQL 이용

### 유효성 검사
- `@Valid` 이용

### 예외 처리
- `@ExceptionHandler`와 `@RestControllerAdvice`를 이용하여 글로벌 예외 처리

### 회원가입 및 로그인
- jwt 이용

## Stack
- Language: Java
- Framework: Spring Boot
- Database: MySQL
- ORM: JPA
