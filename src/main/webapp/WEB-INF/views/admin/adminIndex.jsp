<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/head.jsp" %>
</head>
<body>
<%@ include file="common/sidebar.jsp" %>
<div class="wrapper d-flex flex-column min-vh-100">
<%@ include file="common/header.jsp" %>
	<main class="body flex-grow-1">
        <div class="container-lg px-4">
          <div class="mb-4">
              <div class="card text-white bg-info">
                <div class="card-body pb-0 d-flex justify-content-between align-items-start">
                  <div>
                    <div class="fs-4 fw-semibold">234</div>
                    <div>최근 1주일 가입 유저</div>
                  </div>
                </div>
                <div class="c-chart-wrapper mt-3 mx-3" style="height:70px;">
                  <canvas class="chart" id="card-chart1" height="70"></canvas>
                </div>
              </div>
          </div>
          <div class="mb-5 table-responsive rounded-2 overflow-hidden border">
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
          
          <div class="mb-4">
              <div class="card text-white bg-dark">
                <div class="card-body pb-0 d-flex justify-content-between align-items-start">
                  <div>
                    <div class="fs-4 fw-semibold">234</div>
                    <div>최근 1주일 게시글 수</div>
                  </div>
                </div>
                <div class="c-chart-wrapper mt-3 mx-3" style="height:70px;">
                  <canvas class="chart" id="card-chart2" height="70"></canvas>
                </div>
              </div>
          </div>

          <div class="mb-5 table-responsive rounded-2 overflow-hidden border" >
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
          
          <div class="mb-4">
              <div class="card text-white bg-success">
                <div class="card-body pb-0 d-flex justify-content-between align-items-start">
                  <div>
                    <div class="fs-4 fw-semibold">234</div>
                    <div>최근 1주일 결제 수</div>
                  </div>
                </div>
                <div class="c-chart-wrapper mt-3 mx-3" style="height:70px;">
                  <canvas class="chart" id="card-chart3" height="70"></canvas>
                </div>
              </div>
          </div>

          <div class="mb-5 table-responsive rounded-2 overflow-hidden border" >
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
      </main>
      <%@ include file="common/footer.jsp" %>
      </div>
      <!-- CoreUI and necessary plugins-->
    <script src="${cp }/admin/js/sub.js"></script>
    <script src="${cp }/admin/js/simplebar.js"></script>
    <script>
      const header = document.querySelector('header.header');

      document.addEventListener('scroll', () => {
        if (header) {
          header.classList.toggle('shadow-sm', document.documentElement.scrollTop > 0);
        }
      });
    </script>

    <!-- Plugins and scripts required by this view-->
    <script src="${cp }/admin/js/chart.umd.js"></script>
    <script src="${cp }/admin/js/chartjs.js"></script>
    <script src="${cp }/admin/js/main.js"></script>
    <script>
      // 카드 차트 1: 유저
  new Chart(document.getElementById('card-chart1'), {
    type: 'bar',
    data: {
      labels: ['7일 전', '6일 전', '5일 전', '4일 전', '3일 전', '2일 전', '오늘'],
      datasets: [{
        data: [5, 7, 4, 8, 6, 9, 10],
        backgroundColor: 'rgba(255,255,255,0.4)',
        borderColor: '#fff',
        borderWidth: 0
      }]
    },
    options: {
      maintainAspectRatio: false,
      plugins: { legend: { display: false } },
      scales: { x: { display: false }, y: { display: false } }
    }
  });

  // 카드 차트 2: 게시글
  new Chart(document.getElementById('card-chart2'), {
    type: 'bar',
    data: {
      labels: ['7일 전', '6일 전', '5일 전', '4일 전', '3일 전', '2일 전', '오늘'],
      datasets: [{
        data: [5, 7, 4, 8, 6, 9, 10],
        backgroundColor: 'rgba(255,255,255,0.4)',
        borderColor: '#fff',
        borderWidth: 0
      }]
    },
    options: {
      maintainAspectRatio: false,
      plugins: { legend: { display: false } },
      scales: { x: { display: false }, y: { display: false } }
    }
  });

  // 카드 차트 3: 결제
  new Chart(document.getElementById('card-chart3'), {
    type: 'bar',
    data: {
      labels: ['7일 전', '6일 전', '5일 전', '4일 전', '3일 전', '2일 전', '오늘'],
      datasets: [{
        data: [5, 7, 4, 8, 6, 9, 10],
        backgroundColor: 'rgba(255,255,255,0.4)',
        borderColor: '#fff',
        borderWidth: 0
      }]
    },
    options: {
      maintainAspectRatio: false,
      plugins: { legend: { display: false } },
      scales: { x: { display: false }, y: { display: false } }
    }
  });
    </script>
</body>
</html>