<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.7/js/bootstrap.min.js" integrity="sha512-zKeerWHHuP3ar7kX2WKBSENzb+GJytFSBL6HrR2nPSR1kOX1qjm+oHooQtbDpDBSITgyl7QXZApvDfDWvKjkUw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.7/css/bootstrap.min.css" integrity="sha512-fw7f+TcMjTb7bpbLJZlP8g2Y4XcCyFZW8uy8HsRZsH/SwbMw0plKHFHr99DN3l04VsYNwvzicUX/6qurvIxbxw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"/>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <link rel="stylesheet" href="${cp }/css/style.css">
</head>
<body>

<div class="container">

	<main class="d-flex justify-content-between mx-0">
            <div class="col-lg-8 px-0" style="max-width: 700px;">
                <!-- 슬라이더 -->
              <div class="mb-4 table-responsive rounded-4 overflow-hidden border">
	            <table class="table m-0 text-center table-borderless">
	              <thead>
	                <tr>
	                  <th class="bg-light py-3">신규회원</th>
	                  <th class="bg-light py-3">방문회원</th>
	                  <th class="bg-light py-3">탈퇴회원</th>
	                  <th class="bg-light py-3">전체회원</th>
	                </tr>
	              </thead>
	              <tbody>
	                <tr>
	                  <td class="py-3"><span class="fw-semibold">0</span> 명</td>
	                  <td class="py-3"><span class="fw-semibold">0</span> 명</td>
	                  <td class="py-3"><span class="fw-semibold">0</span> 명</td>
	                  <td class="py-3"><span class="fw-semibold">0</span> 명</td>
	                </tr>
	              </tbody>
	            </table>
	          </div>
	          
	
	          <div class="mb-4 table-responsive rounded-4 overflow-hidden border" >
	            <table class="table table-borderless m-0 text-center">
	            <thead>
	              <tr>
	                <th class="bg-light py-3">신규 게시글</th>
	                <th class="bg-light py-3">승인대기</th>
	                <th class="bg-light py-3">모금기간 만료</th>
	                <th class="bg-light py-3">전체 게시글</th>
	              </tr>
	            </thead>
	            <tbody>
	              <tr>
	                <td class="py-3 fw-semibold">0</td>
	                <td class="py-3 fw-semibold">0</td>
	                <td class="py-3 fw-semibold">0</td>
	                <td class="py-3 fw-semibold">0</td>
	              </tr>
	            </tbody>
	          </table>
	          </div>
	          
	
	          <div class="mb-4 table-responsive rounded-4 overflow-hidden border" >
	            <table class="table table-borderless m-0 text-center">
	            <thead>
	              <tr>
	                <th class="bg-light py-3">신규 결제</th>
	                <th class="bg-light py-3">환불 요청</th>
	                <th class="bg-light py-3">전체 결제횟수</th>
	                <th class="bg-light py-3">전체 결제금액</th>
	              </tr>
	            </thead>
	            <tbody>
	              <tr>
	                <td class="py-3 fw-semibold">0</td>
	                <td class="py-3 fw-semibold">0</td>
	                <td class="py-3 fw-semibold">0</td>
	                <td class="py-3 fw-semibold">0</td>
	              </tr>
	            </tbody>
	          </table>
          </div>




                
               
            </div>
            <div class="col-lg-4 px-0" style="max-width: 360px;">

                <!-- 로그인 후-->
                <div class="border rounded-3">
                    <div class="p-4 d-flex align-items-center gap-3">
                        <div class="overflow-hidden rounded-circle" style="width: 64px; height: 64px; border: 2px solid var(--border-1);">
                            <img src="img/img1.png" class="object-fit-cover" alt="프로필사진" style="width: 64px; height: 64px;">
                        </div>
                        <p class="fw-semibold fs-6 mb-0 flex-grow-1 text-truncate" style="color: var(--col-4);"><span style="color: var(--col-6);">키다리아저씨</span> 님</p>
                        <a href="#" class="text-decoration-none border px-3 py-1 rounded-5 small" style="color: var(--col-3);">로그아웃</a>
                    </div>
                </div>

                
                <!-- 공지사항 -->
                <div class="border rounded-3 mt-4 p-4 d-flex flex-column gap-4">
                    <div>
                        <div class="d-flex">
                            <p class="fs-5 fw-semibold m-0" style="color: var(--col-6);">회원</p>
                        </div>
                        <ul class="p-0 d-flex flex-column mt-2 mb-0">
                            <li><a href="#" class="text-decoration-none text-truncate py-2  d-block" style="color: var(--col-3);">회원목록 조회</a></li>
                            <li><a href="#" class="text-decoration-none text-truncate py-2  d-block" style="color: var(--col-3);">기관회원 승인</a></li>
                            <li><a href="#" class="text-decoration-none text-truncate py-2  d-block" style="color: var(--col-3);">서브관리자 생성</a></li>
                        </ul>
                    </div>
                    <div>
                        <div class="d-flex">
                            <p class="fs-5 fw-semibold m-0" style="color: var(--col-6);">기부/결제</p>
                        </div>
                        <ul class="p-0 d-flex flex-column mt-2 mb-0">
                            <li><a href="#" class="text-decoration-none text-truncate py-2  d-block" style="color: var(--col-3);">모금함 조회</a></li>
                            <li><a href="#" class="text-decoration-none text-truncate py-2  d-block" style="color: var(--col-3);">기부내역 조회</a></li>
                            <li><a href="#" class="text-decoration-none text-truncate py-2  d-block" style="color: var(--col-3);">결제내역 조회</a></li>
                            <li><a href="#" class="text-decoration-none text-truncate py-2  d-block" style="color: var(--col-3);">환불 관리</a></li>
                        </ul>
                    </div>
                    <div>
                        <div class="d-flex">
                            <p class="fs-5 fw-semibold m-0" style="color: var(--col-6);">게시판</p>
                        </div>
                        <ul class="p-0 d-flex flex-column mt-2 mb-0">
                            <li><a href="#" class="text-decoration-none text-truncate py-2  d-block" style="color: var(--col-3);">기부 게시글 승인</a></li>
                            <li><a href="#" class="text-decoration-none text-truncate py-2  d-block" style="color: var(--col-3);">관리자 글 목록 / 작성</a></li>
                            <li><a href="#" class="text-decoration-none text-truncate py-2  d-block" style="color: var(--col-3);">게시글 관리</a></li>
                            <li><a href="#" class="text-decoration-none text-truncate py-2  d-block" style="color: var(--col-3);">댓글 관리</a></li>
                        </ul>
                    </div>
                    <div>
                        <div class="d-flex">
                            <p class="fs-5 fw-semibold m-0" style="color: var(--col-6);">기타 관리</p>
                        </div>
                        <ul class="p-0 d-flex flex-column mt-2 mb-0">
                            <li><a href="#" class="text-decoration-none text-truncate py-2  d-block" style="color: var(--col-3);">로그인 로그</a></li>
                            <li><a href="#" class="text-decoration-none text-truncate py-2  d-block" style="color: var(--col-3);">탈퇴회원 로그</a></li>
                            <li><a href="#" class="text-decoration-none text-truncate py-2  d-block" style="color: var(--col-3);">이메일 인증 로그</a></li>
                            <li><a href="#" class="text-decoration-none text-truncate py-2  d-block" style="color: var(--col-3);">뱃지 관리</a></li>
                        </ul>
                    </div>
                    
                </div>
                
            </div>
        </main>

</body>
</html>