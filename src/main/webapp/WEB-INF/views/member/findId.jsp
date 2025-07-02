<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
      <main>
   <!-- 카드 형태 박스 -->
<div class="form-box">
  <form method="POST" action="${pageContext.request.contextPath}/member/find-id">
    <!-- 이메일 입력 -->
    <div class="form-floating mb-3">
      <input type="email" class="form-control" id="floatingEmail" name="email" placeholder="이메일">
      <label for="floatingEmail">이메일</label>
    </div>

    <!-- 이름 입력 -->
    <div class="form-floating mb-4">
      <input type="text" class="form-control" id="floatingName" name="name" placeholder="이름">
      <label for="floatingName">이름</label>
    </div>

    <!-- 버튼 -->
    <button type="submit" class="btn btn-main w-100">아이디 찾기</button>
  </form>
</div>

  </main>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>