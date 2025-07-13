# HappyGivers - 기부 플랫폼 프로젝트

기부 문화를 확산시키고, 다양한 사회 문제에 맞춤형 후원을 연결하는 온라인 플랫폼입니다.  
기부 진입 장벽을 낮추고, **투명성**과 **보상 요소**를 강화하여  
참여율을 높이고 **신뢰 기반의 기부 문화**를 조성하고자 합니다.

---

## 개발환경 및 기술스택

| 분류       | 사용 기술 |
|------------|-----------|
| **Backend** | Java 21, JSP/Servlet, MyBatis, HikariCP |
| **Frontend** | HTML5, Bootstrap 5, jQuery, Toast UI Editor |
| **Database** | MariaDB, Redis |
| **API 연동** | PortOne 결제 V2, 다음 주소 API |
| **파일저장소** | AWS S3 (썸네일/본문 이미지 업로드) |
| **기타 라이브러리** | Gson (JSON 처리), flatpickr (날짜 선택), Slf4j (로깅) |

![개발 환경 이미지](https://happygivers-bucket.s3.ap-northeast-2.amazonaws.com/static/tmp/Frame+818069.jpg)

---

## 주요 기능

- 회원가입 및 로그인 (일반/기관/관리자)
- 게시판 분리 (공지사항, Q&A, 기부 게시판, FEED)
- 기부글 등록 시 마감일, 목표금액 설정 + 관리자 승인 필요
- 이미지 업로드 (본문, 썸네일) → AWS S3 관리
- 댓글 등록/수정/삭제 (Ajax 기반 비동기)
- 포트원 결제 연동 (결제 및 환불)
- 관리자 기능 (회원 관리, 기부글 승인, 통계)
- 로그인 제한 처리
- 마크다운 에디터 기반 게시글 작성

---

## 참조자료
- [요구사항 정의서](https://docs.google.com/spreadsheets/d/1Mw6_9nHa_WShSLZLDpSY270aOkuAnVBjDlMinvaG2BA/edit?usp=sharing)
- [WBS 차트](https://docs.google.com/spreadsheets/d/1O80ro_zIlsiNR1VFf_qVBQu40JRB2Tbuilw6EijE2Dc/edit?usp=sharing)
- [DB Diagram](https://dbdiagram.io/d/happygivers-6858fffef039ec6d366905b2)
- [figma ui 프로토타입](https://www.figma.com/proto/4xrkqgVaYwAXS7ogBojyhe/%ED%95%B4%ED%94%BC%EA%B8%B0%EB%B2%84%EC%A6%88?page-id=0%3A1&node-id=1-2&viewport=376%2C-1065%2C0.21&t=rsVs2gs4mY81hfx5-1&scaling=min-zoom&content-scaling=fixed&starting-point-node-id=1%3A2)


## 프로젝트 작업 내역

<details>
<summary>2025.06.27</summary>

- 메인 인덱스 JSP 연결
- 어드민 메인 페이지 등록

</details>

<details>
<summary>2025.06.29</summary>

- 보드 도메인 생성
- Donate 도메인 생성
- Criteria, PageDto, AlertUtil 추가

</details>

<details>
<summary>2025.06.30</summary>

- 게시글 생성 구현 (기부게시글, 회차 함께 생성되도록)
- 헤더 메뉴 클릭 시 사이드바 열리도록 구현
- 회원가입 일부 구현 중

</details>

<details>
<summary>2025.07.01</summary>

- 로그인, 회원가입(기관/일반) 완료
- 기부 게시글 목록 썸네일 구현
- 관리자 후원 승인 페이지 구성 중
- 카테고리명 출력, 후원 승인 기능 설계
- 이메일 인증 및 전송 구현

</details>

<details>
<summary>2025.07.02</summary>

- 게시글 상세 댓글 기능 구현
- 댓글 수정/삭제 버튼 권한 체크
- 어드민 게시판 숨김처리 기능 구현
- 이메일 인증 로직 보완
- 아이디 찾기, 회원가입 인증 처리

</details>

<details>
<summary>2025.07.03</summary>

- 결제 API 연동 테스트
- 회원가입 이메일 인증 처리 보완
- 이메일 인증 후 마이페이지 구현 중

</details>

<details>
<summary>2025.07.04</summary>

- 결제 기능 구현 (donate_action, pay, pay_log)
- 기부게시글 상세 결제 연동 완료
- 마이페이지 보완 (이메일 재인증)

</details>

<details>
<summary>2025.07.05</summary>

- 메인페이지 기부금 총액, 개인기부금 노출
- 피드 기능 초기 구현 (리스트/글쓰기)
- 어드민 기부 게시글 승인 시 회차 상태 반영

</details>

<details>
<summary>2025.07.06</summary>

- 결제 취소 후 DB 반영 및 상태 관리
- 어드민 기부내역, 결제내역 목록 추가
- 비밀번호 변경 구현 (암호화 저장)

</details>

<details>
<summary>2025.07.07</summary>

- 어드민 페이지 환불 처리 기능 구현
- 결제 로그 관리 기능 구현
- 자동 로그인 기능 구현 시작

</details>

<details>
<summary>2025.07.08</summary>

- 게시글 첨부파일 S3 업로드 처리
- 썸네일 S3 경로로 수정
- 피드 댓글 작성/수정/삭제 구현
- 프로필 수정, 닉네임 자동부여 등 마이페이지 개선

</details>

<details>
<summary>2025.07.09</summary>

- 게시글 첨부파일 등록/수정 구현
- S3 고스트 파일 삭제 처리
- 이용약관 동의 기능 구현

</details>

<details>
<summary>2025.07.10</summary>

- 공지사항, QnA 게시판 리스트 및 뷰 구현
- 어드민페이지 프로필 노출 처리
- 통계 데이터 노출 기능 구현

</details>

<details>
<summary>2025.07.11</summary>

- 마이페이지: 기부내역, 결제내역, 내가 쓴 글/댓글 조회 구현
- Feed 수정폼 구현
- 기관회원 승인 기능 보완

</details>

<details>
<summary>2025.07.12</summary>

- 피드 좋아요, 수정, 삭제 기능 구현
- 기부게시글 승인 시 썸네일 삭제 오류 해결
- 기관 소개, 닉네임 없는 경우 처리
- 마이페이지 게시글 목록 UI 개선
- S3에 CSS, 이미지 업로드 처리

</details>

<details>
<summary>2025.07.13</summary>

- 기부 게시글 상세 후원자 top3 닉네임 처리
- 결제 완료 시 메시지 오류 제거

</details>
