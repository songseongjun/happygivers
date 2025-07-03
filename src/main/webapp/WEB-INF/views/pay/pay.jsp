<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp" %>
<script src="https://cdn.portone.io/v2/browser-sdk.js" async defer></script>
</head>

<body>
<%@ include file="../common/header.jsp" %>

	<div class="container px-0">
		<main>
			<div class="border p-4 rounded-4 mt-4">
                <p class="fs-5 fw-semibold">나의 도움이 필요한 후원처</p>
                <div class="form-floating flex-grow-1">
	              <input type="number" class="form-control" placeholder="기부금액" name="totalAmount" id="totalAmount" min="0"  required>
	              <label for="goalamount">기부금액</label>
	            </div>
                <button class="btn btn-primary form-control mt-4 py-2 fs-5 fw-semibold" id="checkOutBtn" onclick="handlePayment()">결제하기</button>
            </div>
          
		</main>
	</div>

<%@ include file="../common/footer.jsp" %>

<script>
  async function handlePayment() {
    try {
      const response = await PortOne.requestPayment({
        storeId: "store-f1ff113e-a12f-48a2-ad88-1a67d77bd7ad", // 스토어아이디
        channelKey: "channel-key-52c3a9b7-5696-4767-8b91-0295776acbd8", // 채널 키
        paymentId: `payment-${crypto.randomUUID()}`, // 주문번호
        orderName: "나이키 와플 트레이너 2 SD", // 기부게시판 타이틀
        totalAmount: 100, // 금액
        currency: "CURRENCY_KRW", // 한화
        payMethod: "CARD" // 결제 타입
      });

      if (response.code === "SUCCESS") {
        alert("✅ 결제 성공! paymentId: " + response.data.paymentId);
        // TODO: 서버에 결제 정보 전송 및 검증
      } else {
        alert("❌ 결제 실패: " + response.message);
      }
    } catch (error) {
      console.error("결제 요청 실패:", error);
      alert("결제 중 오류가 발생했습니다.");
    }
  }
</script>


</body>
</html>