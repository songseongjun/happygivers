<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

    <!-- 일반회원 / 기관회원 선택 -->
    <div class="d-flex border rounded-3 mx-auto mt-5" style="max-width: 460px;">
      <a href="${cp}/member/register?mtype=USER" class="flex-grow-1 btn ${mtype == 'USER' ? 'btn-primary' : ''}">일반회원</a>
      <a href="${cp}/member/register?mtype=ORG" class="flex-grow-1 btn ${mtype == 'ORG' ? 'btn-primary' : ''}">기관회원</a>
    </div>

    <!-- 회원가입 폼 -->
    <form action="/happygivers/member/register" method="post" class="d-flex flex-column mx-auto my-5 gap-4" style="max-width: 460px;">
      <input type="hidden" name="mtype" value="${mtype}">

    <!-- Step 1: 약관동의 -->
				<div class="border rounded-3 list-group p-4 collapse show"
					id="tosForm">

					<!-- 모두 동의 -->
					<div class="form-check mb-3">
						<input class="form-check-input" type="checkbox" id="agreeAll" />
						<label class="form-check-label fw-bold" for="agreeAll">모두
							동의</label>
					</div>

					<!-- 이용약관 -->
					<div class="mb-4">
						<h5 class="fw-bold mb-2">이용약관</h5>
						<div
							style="height: 200px; overflow-y: auto; background: #fff; border: 1px solid var(--border-5); padding: 12px; border-radius: 8px;">
							 <p>
							    회원 탈퇴 시, 관련 법령 및 개인정보처리방침에 따라 해피기버즈가 해당 회원 정보를 일정 기간 보관할 수 있는 경우를 제외하고, 
							    해당 회원 계정과 연결된 게시물(기부 내역, 댓글, 피드 등)을 포함한 모든 데이터는 삭제되며 복구할 수 없습니다. 단, 타 사용자가 저장하거나 공유한 콘텐츠는 그대로 유지됩니다. 
							    <br /><br />
							    해피기버즈는 무료로 다양한 기부/후원 서비스를 제공하며, 일부 화면에 후원 배너 또는 공익 광고가 포함될 수 있습니다. 
							    서비스를 이용하면서 발생하는 데이터 통신료는 이용자의 이동통신 계약에 따라 별도로 부과될 수 있으며, 광고 열람으로 발생하는 추가 비용도 이용자의 부담입니다.
							    <br /><br />
							    해피기버즈는 불편함을 최소화하면서도 더 나은 서비스를 제공할 수 있도록 항상 개선을 고민하고 연구합니다.
							  </p>
						</div>
						<div class="form-check mt-2">
							<input class="form-check-input" type="checkbox" id="tos"
								name="tos" required /> <label class="form-check-label"
								for="tos">[필수] 이용약관에 동의합니다.</label>
						</div>
					</div>

					<!-- 개인정보 수집 및 이용 -->
					<div class="mb-4">
						<h5 class="fw-bold mb-2">개인정보 수집 및 이용</h5>
						<div
							style="height: 200px; overflow-y: auto; background: #fff; border: 1px solid var(--border-5); padding: 12px; border-radius: 8px;">
							 <p>
							    해피기버즈는 회원가입 및 서비스 제공을 위해 아래와 같은 개인정보를 수집합니다.<br /><br />
							
							    수집 항목: 아이디, 비밀번호, 이메일, 이름, 휴대전화 번호, 주소 등<br />
							    수집 목적: 회원 식별, 후원 참여 내역 확인, 마이페이지 제공, 서비스 개선<br />
							    보유 기간: 회원 탈퇴 후 5년까지 보관하며, 이후 안전하게 파기됩니다.
							    <br /><br />
							    자세한 사항은 개인정보처리방침을 참고해 주세요.
							  </p>
							</div>
						<div class="form-check mt-2">
							<input class="form-check-input" type="checkbox" id="privacy"
								name="privacy" required /> <label class="form-check-label"
								for="privacy">[필수] 개인정보 수집 및 이용에 동의합니다.</label>
						</div>
					</div>

					<!-- 다음 버튼 -->
					<div class="d-grid">
						<button class="btn btn-primary" type="button"
							onclick="goNextStep()">다음</button>
					</div>
				</div>


				<!-- Step 2: 아이디, 비밀번호, 이메일 -->
      <div class="border rounded-3 list-group p-3 gap-3 collapse" id="step2_account">
        <div class="form-floating my-2">
          <input type="text" class="form-control" placeholder="아이디" name="id" id="id" required>
          <label for="id">아이디</label>
        </div>

        <div class="form-floating my-2">
          <input type="password" class="form-control" placeholder="비밀번호" name="pw" id="pw" required>
          <label for="pw">비밀번호</label>
        </div>

        <div class="form-floating input-group my-2">
          <input type="email" class="form-control" name="email" id="email" placeholder="이메일" required>
          <label for="email" style="z-index: 10;">이메일</label>  
        </div>
        <!-- 일반회원: 닉네임 자동 생성 (서버에서 처리, 클라이언트는 hidden) -->
			  <c:if test="${mtype == 'USER'}">
			    <input type="hidden" name="nickname" value="" id="nickname-hidden" />
			  </c:if>
			
			  <!-- 기관회원: 기관명 입력 필드 -->
			  <c:if test="${mtype == 'ORG'}">
			    <div class="form-floating my-2">
			      <input type="text" class="form-control" name="orgname" id="orgname" placeholder="기관명" required>
			      <label for="orgname">기관명</label>
			    </div>
			  </c:if>


        <div class="d-grid">
          <button type="submit" class="btn btn-primary btn-lg btn-block">회원가입</button>
        </div>
      </div>
      <c:if test="${mtype == 'ORG' }">
      	<input type="hidden" name="status" value="READY">
      </c:if>
    </form>
  </main>
</div>

<%@ include file="../common/footer.jsp" %>

<!-- JS: 동의 전체선택, 단계전환, 이메일인증 -->
<script>
  // 이용약관 동의 후 다음단계 전환
  function goNextStep() {
    if (!document.querySelector("#tos").checked || !document.querySelector("#privacy").checked) {
      alert("필수 약관에 동의해주세요.");
      return;
    }

    $('#tosForm').stop().animate({ opacity: 0, height: 0 }, 300, function () {
      $(this).hide();

      $('#step2_account').css({ opacity: 0, display: 'block', height: 'auto' });
      const actualHeight = $('#step2_account').outerHeight();
      $('#step2_account').css({ height: 0 });

      $('#step2_account').animate({ opacity: 1, height: actualHeight }, 400, function () {
        $(this).css({ height: 'auto' });
      });
    });
  }

  // 모두 동의 클릭 시 전체 체크
  $('#agreeAll').click(e => {
    const checked = e.target.checked;
    $('#tos, #privacy').prop('checked', checked);
  });

  // 이메일 인증 전송
  document.querySelector("#emailckplz").addEventListener("click", function () {
    const email = document.querySelector("#email").value.trim();
    if (!email) {
      alert("이메일을 입력해주세요.");
      return;
    }
    fetch("/happygivers/member/email-auth?email=" + encodeURIComponent(email))
      .then(res => res.text())
      .then(msg => alert("인증메일이 전송되었습니다. 5분 이내 인증해주세요."));
  });
</script>

</body>
</html>
