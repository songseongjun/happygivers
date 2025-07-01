<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  String mtype = request.getParameter("mtype");
  if (mtype == null) mtype = "USER";
  request.setAttribute("mtype", mtype); 
%>
    
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp" %>
</head>
<body>
<%@ include file="../common/header.jsp" %>

	<div class="container">
      <main>
        <h2 class="text-center">회원가입</h2>
        <div class="d-flex border rounded-3 mx-auto mt-5" style="max-width: 460px;">
          <a href="${cp}/member/register?mtype=USER" class="flex-grow-1 btn ${mtype == 'USER' ? 'btn-primary' : ''}">일반회원</a>
          <a href="${cp }/member/register?mtype=ORG" class="flex-grow-1 btn ${mtype == 'ORG' ? 'btn-primary' : ''}">기관회원</a>
        </div>
        <form action="/happygivers/member/register" method="post"
      	class="d-flex flex-column mx-auto my-5 gap-4" style="max-width: 460px;">
      	
				<input type="hidden" name="mtype" value="${mtype}">
				
            <div class="d-flex border rounded-3 list-group p-3 gap-3">
              <div class="form-floating">
                <input type="text" class="form-control" placeholder="아이디" name="id" id="id">
                <label for="id">아이디</label>
              </div>
  
              <div class="form-floating">
                <input type="password" class="form-control" placeholder="비밀번호" name="pw" id="pw">
                <label for="pw">비밀번호</label>
              </div>
              <!-- 이메일인증발송 -->
              <div class="form-floating input-group">
              <input type="text" class="form-control" name="email" id="email" placeholder="이메일">
              <label for="email">이메일</label>
              <button type="button" id="emailckplz" class="btn btn-outline-primary">이메일 인증</button>
              </div>
              
              <div class="form-floating input-group">
                <input type="text" class="form-control" placeholder="인증번호입력" name="emailck" id="emailck">
                <label for="emailck" style="z-index: 10;">인증번호 입력</label>
                <button class="btn btn-outline-primary" id="emailcked">인증번호 확인</button>
              </div>
            </div>
            
            <div class="d-flex border rounded-3 list-group p-3 gap-3">
              <div class="form-floating">
                <input type="text" class="form-control" placeholder="이름" name="name" id="name" autocomplete="name">
                <label for="name">이름</label>
              </div>
              
              <div class="form-floating">
                <input type="text" class="form-control" placeholder="전화번호" name="tel" id="tel" autocomplete="tel">
                <label for="tel">전화번호</label>
              </div>
              <div class="form-floating input-group">
                <input type="text" class="form-control" id="roadAddress" name="roadAddress" placeholder="도로명 주소 또는 지번 주소" readonly>
                <label for="roadAddress" style="z-index: 10;">도로명 주소 또는 지번 주소</label>
                
                
                <button class="btn btn-outline-primary" type="button" onclick="execDaumPostcode()">주소 검색</button>
              </div>
              <div class="form-floating ">
                <input type="text" class="form-control" id="detailAddress" name="detailAddress" placeholder="상세 주소">
                <label for="detailAddress">상세 주소</label>
                
                
              </div>
                <input type="hidden" name="location" id="location" />
            </div>
              
            <div class="d-flex border rounded-3 list-group p-3 gap-3" id="tosForm">
              <div class="form-check mb-2">
                <input class="form-check-input" type="checkbox" id="agreeAll">
                <label class="form-check-label fw-bold" for="agreeAll">모두 동의</label>
              </div>
  
             <div class="form-check">
                <input class="form-check-input" type="checkbox" id="tos" required>
                <label class="form-check-label" for="tos">
                  <span class="text-primary text-decoration-none fw-bold me-1">[필수]</span> <a href="#" target="_blank" style="color: var(--col-5);"> 이용약관 동의</a>
                </label>
              </div>
  
              <div class="form-check">
                <input class="form-check-input" type="checkbox" id="privacy" required>
                <label class="form-check-label" for="privacy">
                  <span class="text-primary text-decoration-none fw-bold me-1">[필수]</span> <a href="#" target="_blank" style="color: var(--col-5);">개인정보 수집 및 이용 동의</a>
                </label>
              </div>
            </div>
            <button type="submit" class="btn btn-primary btn-lg">회원가입</button>
          		
        </form>
      </main>
    </div>
<%@ include file="../common/footer.jsp" %>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
$(document).ready(function(){
	  $("#roadAddress, #detailAddress").on("input", function(){
	    const fullAddr = $("#roadAddress").val() + " " + $("#detailAddress").val();
	    $("#location").val(fullAddr);
	  });
	});



document.querySelector("#emailckplz").addEventListener("click", function() {
	  const email = document.querySelector("#email").value;
	  fetch("/happygivers/email/send?email=" + email)
	    .then(res => res.text())
	    .then(msg => alert("메일이 전송되었습니다. 5분 내에 인증해주세요!"));
	});

</script>

</body>
</html>