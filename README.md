# Wanted-pre-onboarding-backend

<br>

- # 지원자의 성명 : 이대호

- ## 애플리케이션의 실행 방법(엔드포인트 호출 방법 포함)
  - ## AWS-EC2(Amazon-Linux 프리티어) 환경 구조
      - AWS-EC2 API 주소 http://3.37.97.1:8081
    ![image](https://github.com/TaehoLEEKR/wanted-pre-onboarding-backend/assets/80689135/0f3c272b-7fb6-4c8c-be70-fe79f5f6b5bd)
      - ### 실행방법
      ```
      sudo systemctl enable docker // 도커 활성화
      sudo systemctl start docker // 도커 실행
      ```
      ```
      git pull origin main // 깃 허브 레파지토리 pull or git clone 
      ```
      ```
      docker-compose build // docker-compose.yml 파일을 빌드
      docker-compose up -d // docker-compose 를 docker에 올리고 백그라운드로 실행
      ```
      ```
      docker-compose ps // 도커 확인
      ```
      ![image](https://github.com/TaehoLEEKR/wanted-pre-onboarding-backend/assets/80689135/454158f0-405a-4331-8fe7-b3fa53f3f46e)


- ## 데이터베이스 테이블 구조
   - ## Community ERD
     ![image](https://github.com/TaehoLEEKR/wanted-pre-onboarding-backend/assets/80689135/e651695e-5bc7-4f5b-aa2f-64276426d047)
       - 게시판 - 사용자 N-1 관계
       - 한명의 사용자가 필수적으로 존재하며 사용자는 여러개의 게시판을 만들수 있다.


- ## 구현한 API동작을 촬영한 데모 영상 링크
   - ### [데모 영상 Swagger TEST](https://www.youtube.com/watch?v=IVEfx8pB2Bo)


- ## 구현 방법 및 이유에 대한 간략한 설명
  - ### 과제 1. 사용자 회원가입 엔드포인트 ([POST]/api/users/sign-up)
    - #### 이메일 조건 : 회원가입과 로그인 에 사용되는 userForm, LoginForm 에 @Email 어노테이션으로 @포함을 추가 하고 Controller 에서 @Valid 어노테이션을 사용하여 유효성 검사를 진행
    - #### 비밀번호 조건 : 회원가입과 로그인시 입력받은 비밀번호의 길이가 8 미만 이면 에러를 출력 8자 이상일 경우 Spring Security 를 사용하여 SecurityConfig 에 PasswordEncorder 를 Bean등록 후 암호화 후 저장.
  - ### 과제 2. 사용자 로그인 엔드포인트([POST]/api/users/sign-in)
    - #### 올바른 로그인 시 JWT 토큰 생성 : 로그인시 회원가입 생성과 같은 유효성 검사를 진행하고 중복이메일을 JPA를 사용하여 체크 로그인 pw는 passwordEncoder.matches 를 사용하여 비교후 검증
    - #### 로그인 유효성 검사 통과후 User의 ID를 가지고 jwt token 발행 진행 (generate(기본토큰,리프레쉬토큰,타입,유효기간) > 발급
  - ### 과제 3. 새로운 게시글을 생성하는 엔드포인트 ([POST] /api/community/create )
      - ##### 생성하기 위해서는 누가 게시글을 작성하는지 와 어떤 게시글을 만드는지 필요하므로 user 와 community를 조인하여 저장해야한다
      - #### 생성시 현재 UserId를 전달 받아 community <> user 조인 후 생성 저장.
  - ### 과제 4. 게시글 목록을 조회하는 엔드포인트 ([GET] /api/community/list )
      - #### Pageable 을 활용하여 pagnation을 진행 sort 기준은 createDate 생성 날짜 
  - ### 과제 5. 특정 게시글을 조회하는 엔드포인트([GET] /api/community/{communityId})
      - ##### 특정 게시글을 조회하기 위해서는 어떤 게시글을 조회하기 위해필요한 고유값 communityId 가 필요하여 @PathVariable 로 Url로 전달한다. 
      - #### @PathVariable 어노테이션으로 communityID를 전달 받아 JPA로 조회
  - ### 과제 6. 특정 게시글을 수정하는 엔드포인트([PUT] /api/community/{communityId})
      - #### @PathVariable 어노테이션으로 communityID를 전달 받아 JPA로 수정
      - #### @LoginUser CurrentUser loginUser 을 전달 받아 현재 로그인 한 사람의 UserID를 전달 받아 수정
  - ### 과제 7. 특정 게시글을 삭제하는 엔드포인트([DELETE] /api/community/{communityId})
      - #### @PathVariable 어노테이션으로 communityID를 전달 받아 JPA로 수정
      - #### @LoginUser CurrentUser loginUser 을 전달 받아 현재 로그인 한 사람의 UserID를 전달 받아 삭제
   
  - ##### @LoginUser -> 파라미터로 전달받아 현재 누가 로그인 하였는지 구분하기 위해 @interface 로 구현하여 사용함
  - ##### CurrentUser -> @LoginUser 어노테이션을 전달 받을 DTO UserId를 알기 위해 구현
 
    
- ## API 명세(request/response 포함)
  - ### user-controller
    - ####  ([POST] /api/users/sign-up )
      - request Body(JSON)
        - {
            "username" : "사용자이름",
            "email" : "이메일 주소",
            "password" : "비밀번호",
            "phone" : "전화번호"
          }
      - response 200 : 회원 가입 성공 하였습니다.
      - response 400 : {"message": "@ 이메일 형식에 맞지 않습니다","errorCode": "USERFORM_EMAIL"}
      - {"message": "이미 존재하는 이메일입니다.","errorCode": "ALREADY_USER_EMAIL"}
      - {"message": "이미 존재하는 이메일입니다.","errorCode": "ALREADY_USER_EMAIL"}
    - #### ([POST] /api/users/sign-in )
       - request Body(JSON)
          - {
            "email" : "이메일 주소",
            "password" : "비밀번호",
          }
      - response 200 : {
                        "jwtAccessToken : "JWTTOKEN",
                        "refreschToken : "리프레쉬토큰",
                        "grantType" : "타입",
                        "expiresln" : "만료시간"
                      }
      - response 400 : { "message": "패스워드 길이를 8자 이상 작성해 주세요", "errorCode": "WRONG_PASSWORD_INFO"}
      - {"message": "잘못 된 패스워드 입니다.","errorCode": "WRONG_PASSWORD_LOGIN"}
      - {"message": "@ 이메일 형식에 맞춰주세요.", "errorCode": "LOGINFORM_EMAIL"}
   
    - #### ([GET] /api/users/{jwtToken} )
      - request Path (String)
      - response 200 : {"userId": userId,"email": "이메일","password": "암호화된 비밀번호","userName": "사용자 이름","phone": "전화번호"}
     
  - ### community-controller
      - #### ([GET] /api/community/list)
        - request Parm(String) sort
        - request Parm(Integer) page
        - request Parm(Integer) size
        - response 200 : 
{
  "communities": [
    {
      "communityId": 0,
      "communityName": "string",
      "createdDate": "2023-08-04T13:08:00.344Z",
      "userId": 0
    }
  ],
  "totalPages": 0
}
      - #### ([GET] /api/community/{communityId})
        - request Path(Long)
        - response 200 :{"communityName": "게시판이름","content": "게시판내용","userName": "사용자이름","createDate": "등록날짜"}
        - response 400 : {"message": "게시판이 존재하지 않습니다.","errorCode": "NOT_FIND_COMMUNITY_ID"}
      - #### ([POST] /api/community/create)
        - request Parm(Long)
        - request Body(JSON)
          {
  "communityName": "게시글 이름",
  "content": "게시글 내용"
}
        - response 200 : **처리메세지 없음**
        - response 400 :{
  "message": "존재 하지 않는 유저 입니다.",
  "errorCode": "NOT_FIND_USER_ID"
}
      - #### ([DELETE] /api/community/{communityId})
        - request Path (Long) (communityId)
        - request Parm (Long) (userId)
        - response 200 : **처리메세지 없음**
        - response 400 :{"message": "게시판을 삭제 할 수 없습니다.","errorCode": "NOT_FIND_COMMUNITY_DELETE"}
      - #### ([PUT] /api/community/{communityId})
        - request Path (Long) (communityId)
        - request Parm (Long) (userId)
        - request Body (JSON)
         -{"communityName": "string","content": "string"}
        - response 200 : **처리메세지 없음**
        - response 400 :{"message": "게시판을 수정 할 수 없습니다.","errorCode": "NOT_FIND_COMMUNITY_UPDATE"}
        
