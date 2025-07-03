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
	            <div>
			    <img id="itemImage" src="" alt="이미지" width="200"><br>
			    <strong id="itemName"></strong><br>
			    <span class="price-value"></span>
			  </div>
                <button class="btn btn-primary form-control mt-4 py-2 fs-5 fw-semibold" id="checkOutBtn">결제하기</button>
            </div>
          
		</main>
	</div>

<%@ include file="../common/footer.jsp" %>
	
 <script> 
	let donate = null;
    async function loadDonate() {
      const res = await fetch("${cp}/api/donate?bno=${board.bno}");
      donate = await res.json();
      return donate;
    }

    async function requestPayment(donate) {
	  const paymentId = 'payment-' + crypto.randomUUID();
	  console.log(paymentId);
	  
      const result = await PortOne.requestPayment({
        storeId: donate.storeId,
        channelKey: donate.channelKey,
        paymentId: paymentId,
        orderName: donate.board.title,
        totalAmount: 100,
        currency: "CURRENCY_KRW",
        payMethod: "CARD",
        customData: {
          mno: "${member.mno}",
          bno: donate.board.bno
        }
      });

      if (result.code !== undefined) {
        alert("❌ 결제 실패: " + result.message);
        return;
      }

      const complete = await fetch("${cp}/api/payment/complete", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({ paymentId: result.paymentId })
      });

      if (complete.ok) {
        const json = await complete.json();
        if (json.status === "PAID") {
          alert("✅ 결제 완료!");
        } else {
          alert("❌ 결제는 되었지만 상태 확인 실패");
        }
      } else {
        alert("❌ 서버 검증 실패");
      }
    }
    
    $("#checkOutBtn").on("click", async() => {
      const donate = await loadDonate();
      $("#itemName").text(donate.board.title);
      $("#itemImage").attr("src", donate.board.thumbnail);
      $(".price-value").text('100'.toLocaleString() + "원");

      await requestPayment(donate);
    })

  </script>


</body>
</html>