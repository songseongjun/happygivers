<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  String mtype = request.getParameter("mtype");
  if (mtype == null) mtype = "USER";
  request.setAttribute("mtype", mtype); 
%>
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
		<div class="border rounded-3 list-group p-3 gap-3 collapse show" id="tosForm" >
              <div class="form-check mb-2">
                <input class="form-check-input" type="checkbox" id="agreeAll" name="agreeAll">
                <label class="form-check-label fw-bold" for="agreeAll">모두 동의</label>
              </div>

             <div class="form-check">
                <input class="form-check-input" type="checkbox" id="tos" name="tos" required>
                <label class="form-check-label" for="tos">
                  <span class="text-primary text-decoration-none fw-bold me-1">[필수]</span> <a href="#" target="_blank" style="color: var(--col-5);"> 이용약관 동의</a>
                </label>
              </div>

              <div class="form-check">
                <input class="form-check-input" type="checkbox" id="privacy" name="privacy" required>
                <label class="form-check-label" for="privacy">
                  <span class="text-primary text-decoration-none fw-bold me-1">[필수]</span> <a href="#" target="_blank" style="color: var(--col-5);">개인정보 수집 및 이용 동의</a>
                </label>
              </div>
              
              <button class="btn btn-primary mt-3" type="button" onclick="goNextStep()" >다음</button>
            </div>	
            
            <div class="border rounded-3 list-group p-3 gap-3 collapse" id="step2_account">
              <div class="form-floating my-2">
                <input type="text" class="form-control" placeholder="아이디" name="id" id="id">
                <label for="id">아이디</label>
              </div>

              <div class="form-floating my-2">
                <input type="password" class="form-control" placeholder="비밀번호" name="pw" id="pw">
                <label for="pw">비밀번호</label>
              </div>
              <!-- 이메일인증발송 -->
              <div class="form-floating my-2">
              <input type="text" class="form-control" name="email" id="email" placeholder="이메일">
              <label for="email" style="z-index: 10;">이메일</label>
              </div>
              <div class="d-grid">
              	<button type="submit" class="btn btn-primary btn-lg btn-block">회원가입</button>
               </div>
            </div>
            <script>
			  function goNextStep() {
				  $('#tosForm').stop().animate({ opacity: 0, height: 0 }, 300, function () {
				      $(this).hide();

				      // 초기화
				      $('#step2_account').css({ opacity: 0, display: 'block', height: 'auto' });
				      const actualHeight = $('#step2_account').outerHeight();
				      $('#step2_account').css({ height: 0 });

				      $('#step2_account').animate({ opacity: 1, height: actualHeight }, 400, function () {
				        $(this).css({ height: 'auto' }); // auto로 복원
				      });
				    });
			  }
			</script>
        </form>
      </main>
    </div>
<%@ include file="../common/footer.jsp" %>
<script>
$(_ => {
    // 모두 동의 체크 시 다른것도 체크
    $('#agreeAll').click(_ => {
      if($(event.target).prop("checked")){
        $("#tosForm input").each((v,i) => $(i).prop("checked", "true"))
      }
    })

    $("#emailckplz").click(function(){
      event.preventDefault();
    })
    
    $("#emailcked").click(function(){
      event.preventDefault();
    })

    
  });
</script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

</body>
</html>
