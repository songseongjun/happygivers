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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.7/js/bootstrap.bundle.min.js" integrity="sha512-Tc0i+vRogmX4NN7tuLbQfBxa8JkfUSAxSFVzmU31nVdHyiHElPPy2cWfFacmCJKw0VqovrzKhdd2TSTMdAxp2g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>
<body>
<%@ include file="common/header.jsp" %>
<div class="container px-0">

	<main class="d-flex justify-content-between mx-0">
            <div class="col-lg-8 px-0" style="max-width: 860px;">
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
            <%@ include file="common/adminmenu.jsp" %>
        </main>
	</div>
	<%@ include file="../common/footer.jsp" %>
</body>
</html>