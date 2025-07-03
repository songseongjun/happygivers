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

	<div id="root" class="container px-0">
      <dialog id="loadingDialog" open>
        <article aria-busy="true">결제 정보를 불러오는 중입니다.</article>
      </dialog>
      <main id="checkoutDialog" style="display: none">
        <form id="checkoutForm">
          <article>
            <div class="item">
              <div class="item-image">
                <img id="itemImage" />
              </div>
              <div class="item-text">
                <h5 id="itemName"></h5>
                <p class="price-value"></p>
              </div>
            </div>
            <div class="price">
              <label>총 구입 가격</label>
              <span class="price-value" />
            </div>
          </article>
          <button id="checkoutButton" type="submit">결제</button>
        </form>
      </main>
      <dialog id="failDialog">
        <header>
          <h1>결제 실패</h1>
        </header>
        <p />
        <button type="button" class="closeDialog">닫기</button>
      </dialog>
      <dialog id="successDialog">
        <header>
          <h1>결제 성공</h1>
        </header>
        <p>결제에 성공했습니다.</p>
        <button type="button" class="closeDialog">닫기</button>
      </dialog>
    </div>

<%@ include file="../common/footer.jsp" %>
<script>
      const checkout = new Checkout()
      checkout.load()

      function Checkout() {
        let item = null
        this.load = async () => {
          const waitPortOne = new Promise((resolve) => {
            const polling = setInterval(() => {
              if (window.PortOne != null) {
                clearInterval(polling)
                resolve()
              }
            }, 50)
          })
          const waitItem = await fetch("/api/item").then(
            async (response) => (item = await response.json()),
          )
          await Promise.all([waitPortOne, waitItem])
          window.loadingDialog.open = false
          window.checkoutDialog.open = true
          await this.showCheckout()
        }
        this.showCheckout = async () => {
          window.itemName.replaceChildren(item.name)
          window.itemImage.src = `/${item.id}.png`
          for (const priceElement of document.querySelectorAll(
            ".price-value",
          )) {
            priceElement.replaceChildren(`${item.price.toLocaleString()}원`)
          }
          window.checkoutDialog.onsubmit = async (e) => {
            e.preventDefault()
            this.setWaitingPayment(true)

            function randomId() {
              return [...crypto.getRandomValues(new Uint32Array(2))]
                .map((word) => word.toString(16).padStart(8, "0"))
                .join("")
            }

            const paymentId = randomId()
            const payment = await PortOne.requestPayment({
              storeId: "store-e4038486-8d83-41a5-acf1-844a009e0d94",
              channelKey: "channel-key-4ca6a942-3ee0-48fb-93ef-f4294b876d28",
              paymentId,
              orderName: item.name,
              totalAmount: item.price,
              currency: item.currency,
              payMethod: "CARD",
              customData: {
                item: item.id,
              },
            )
            if (payment.code !== undefined) {
              this.setWaitingPayment(false)
              console.log(payment)
              this.openFailDialog(payment.message)
            }
            const completeResponse = await fetch("/api/payment/complete", {
              method: "POST",
              headers: {
                "Content-Type": "application/json",
              },
              body: JSON.stringify({
                paymentId: payment.paymentId,
              }),
            })
            this.setWaitingPayment(false)
            if (completeResponse.ok) {
              const paymentComplete = await completeResponse.json()
              if (paymentComplete.status === "PAID") {
                this.openSuccessDialog()
              }
            } else {
              this.openFailDialog(await completeResponse.text())
            }
          }
          for (const dialogButton of document.getElementsByClassName(
            "closeDialog",
          )) {
            dialogButton.onclick = () => {
              dialogButton.parentElement.parentElement.open = false
            }
          }
          window.checkoutDialog.style = ""
        }
        this.setWaitingPayment = (isWaiting) => {
          window.checkoutButton.setAttribute("aria-busy", isWaiting.toString())
          window.checkoutButton.disabled = isWaiting
        }
        this.openFailDialog = (message) => {
          window.failMessage.replaceChildren(message)
          window.failDialog.open = true
        }
        this.openSuccessDialog = () => {
          window.successDialog.open = true
        }
      }
    </script>
</body>
</html>