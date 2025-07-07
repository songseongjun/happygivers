<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/head.jsp" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.7/js/bootstrap.min.js" integrity="sha512-zKeerWHHuP3ar7kX2WKBSENzb+GJytFSBL6HrR2nPSR1kOX1qjm+oHooQtbDpDBSITgyl7QXZApvDfDWvKjkUw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.7/css/bootstrap.min.css" integrity="sha512-fw7f+TcMjTb7bpbLJZlP8g2Y4XcCyFZW8uy8HsRZsH/SwbMw0plKHFHr99DN3l04VsYNwvzicUX/6qurvIxbxw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"/>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <link rel="stylesheet" href="${cp }/css/style.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.7/js/bootstrap.bundle.min.js" integrity="sha512-Tc0i+vRogmX4NN7tuLbQfBxa8JkfUSAxSFVzmU31nVdHyiHElPPy2cWfFacmCJKw0VqovrzKhdd2TSTMdAxp2g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<div class="container px-0">

	<main class="d-flex justify-content-between mx-0">
            <div class="px-0" style="max-width: 860px; width:100%;">
              <div>
				<ul class="list-group small">
				<li class="list-group-item ">
					<div class="row my-0 align-items-center">
					<span class="col-2 text-center fw-bold">결제자 명</span>
					<span class="col-2 text-center fw-bold">결제수단</span>
					<span class="col text-center fw-bold">금액</span>
					<span class="col-2 text-center fw-bold">결제 생성 / 마감일</span>
					<span class="col-2 text-center fw-bold">상태</span>
					<span class="col-1 text-center fw-bold">관리</span>
					</div>
				</li>
				<c:forEach items="${pays }" var="p">
				<li class="list-group-item">
					<div class="row my-0 align-items-center">
						<span class="col-2 text-center">${p.name }</span>
						<span class="col-2 text-center">${p.paytype }</span>
						<span class="col text-center"><fmt:formatNumber value="${p.payamount}" />원</span>
						<span class="col-2 text-center">${p.voiddate != null ? p.voiddate : p.regdate}</span>
						<span class="col-2 text-center">${p.paystatus }</span>
						<div class="btn-group btn-group-sm col-1">
							<c:if test="${p.paystatus == 'PAID' }">
							<a href="#" class="btn btn-outline-secondary" data-uuid="${p.uuid }">환불</a>
							</c:if>
						</div>
					</div>
				</li>
				</c:forEach>
				</ul>

			</div>
          </div>

            <%@ include file="../common/adminmenu.jsp" %>
            
        </main>
</div>



                
               
        <%@ include file="../../common/footer.jsp" %>
        
<script>
$('#deleteBtn').on('click', function () {
	  const checkedValues = $('.checkItem:checked').map(function () {
	    return $(this).val();
	  }).get();

	  if (checkedValues.length === 0) {
	    alert('삭제할 항목을 선택하세요.');
	    return;
	  }

	  if (!confirm('정말 삭제하시겠습니까?')) return;

	  // 삭제 요청 전송
	  $.ajax({
	    url: `${cp}/admin/board/delete`,
	    method: 'POST',
	    traditional: true, // 배열을 bno=1&bno=2 형태로 전송
	    data: { bno: checkedValues },
	    success: function (res) {
	      alert('삭제되었습니다.');
	      location.reload();
	    },
	    error: function () {
	      alert('삭제 실패');
	    }
	  });
	});

</script>
</body>
</html>