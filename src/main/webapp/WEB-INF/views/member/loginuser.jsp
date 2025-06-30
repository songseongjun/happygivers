<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp" %>
</head>
<body>
<%@ include file="../common/header.jsp" %>
	<div class="container px-0">
       <main>
        <h2 class="text-center">로그인</h2>
        <div class="d-flex border rounded-3 mx-auto mt-5" style="max-width: 460px;">
          <a href="#" class="flex-grow-1 btn btn-primary">일반회원</a>
          <a href="#" class="flex-grow-1 btn">기관회원</a>
        </div>
        <form method="POST" class="d-flex flex-column mx-auto my-5 gap-4" style="max-width: 460px;">

            <div class="d-flex border rounded-3 list-group p-3 gap-3">
              <div class="form-floating">
                <input type="text" class="form-control" placeholder="아이디" name="id" id="id">
                <label for="id">아이디</label>
              </div>
  
              <div class="form-floating">
                <input type="password" class="form-control" placeholder="비밀번호" name="pw" id="pw">
                <label for="pw">비밀번호</label>
              </div>
              <div class="form-check form-switch">
				 <input class="form-check-input" type="checkbox" id="autologin" name="autologin" value="yes" checked>
				<label class="form-check-label" for="autologin">자동로그인</label>
				</div>
			</div>
            <input type="hidden" name="mtype" value="USER">
			      <button class="btn btn-primary btn-lg">로그인</button>
          
        </form>
      </main>
       
    </div>
<%@ include file="../common/footer.jsp" %>
<script>
        const swiper = new Swiper('.swiper', {
        // Optional parameters
        // direction: 'vertical',
        loop: true,

        // If we need pagination
        pagination: {
            el: '.swiper-pagination',
        },
    });
    </script>
</body>
</html>