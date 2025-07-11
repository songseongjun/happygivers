<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="sidebar sidebar-fixed border-end" id="sidebar">
      <!-- 사이드바 헤더 -->
      <div class="sidebar-header border-bottom ps-4">
        <div class="sidebar-brand">
          <a href="#"><img src="${cp }/img/logo.svg" alt="Happy Givers"></a>
        </div>
      </div>
      <!-- 위젯 메인 영역 -->
      <ul class="sidebar-nav" data-coreui="navigation" data-simplebar="">
        <li class="nav-item"><a class="nav-link" href="index.html">
            <svg class="nav-icon">
              <use xlink:href="${cp }/admin/img/free.svg#cil-speedometer"></use>
            </svg>홈</a></li>

          <li class="nav-title">회원 승인 관리</li>
          <li class="nav-group">
              <a class="nav-link nav-group-toggle" href="#">
                  <svg class="nav-icon">
                      <use xlink:href="${cp }/admin/img/free.svg#cil-people"></use>
                  </svg> 승인관리
              </a>
              <ul class="nav-group-items compact">
                  <li class="nav-item">
                      <a class="nav-link" href="${cp}/admin/orgapprove">
                          <span class="nav-icon"><span class="nav-icon-bullet"></span></span> 기관회원 승인
                      </a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="${cp}/admin/orgstatus">
                          <span class="nav-icon"><span class="nav-icon-bullet"></span></span> 기관회원 승인 이력
                      </a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="#">
                          <span class="nav-icon"><span class="nav-icon-bullet"></span></span> 서브관리자 생성
                      </a>
                  </li>
              </ul>
          </li>

          </li>
        <li class="nav-title">결제/기부관리</li>
        <li class="nav-group"><a class="nav-link nav-group-toggle" href="#">
            <svg class="nav-icon">
              <use xlink:href="${cp }/admin/img/free.svg#cil-star"></use>
            </svg> 결제</a>
          <ul class="nav-group-items compact">
            <li class="nav-item"><a class="nav-link" href="#"><span class="nav-icon"><span class="nav-icon-bullet"></span></span> 결제내역 조회</a></li>
            <li class="nav-item"><a class="nav-link" href="#"><span class="nav-icon"><span class="nav-icon-bullet"></span></span> 기부내역 조회</a></li>
            <li class="nav-item"><a class="nav-link" href="#"><span class="nav-icon"><span class="nav-icon-bullet"></span></span> 환불 관리</a></li>
          </ul>
        </li>
        <li class="nav-title">게시글 관리</li>
        <li class="nav-group"><a class="nav-link nav-group-toggle" href="#">
            <svg class="nav-icon">
              <use xlink:href="${cp }/admin/img/free.svg#cil-notes"></use>
            </svg> 게시판</a>
          <ul class="nav-group-items compact">
            <li class="nav-item"><a class="nav-link" href="#"><span class="nav-icon"><span class="nav-icon-bullet"></span></span> 관리자 글 목록 / 작성</a></li>
            <li class="nav-item"><a class="nav-link" href="#"><span class="nav-icon"><span class="nav-icon-bullet"></span></span> 기부 게시글 승인</a></li>
            <li class="nav-item"><a class="nav-link" href="#"><span class="nav-icon"><span class="nav-icon-bullet"></span></span> 게시글 관리</a></li>
            <li class="nav-item"><a class="nav-link" href="#"><span class="nav-icon"><span class="nav-icon-bullet"></span></span> 댓글 관리</a></li>
          </ul>
        </li>
        <li class="nav-title">기타 관리</li>
        <li class="nav-group"><a class="nav-link nav-group-toggle" href="#">
            <svg class="nav-icon">
              <use xlink:href="${cp }/admin/img/free.svg#cil-bell"></use>
            </svg> 로그 / 배지 등 </a>
          <ul class="nav-group-items compact">
            <li class="nav-item"><a class="nav-link" href="#"><span class="nav-icon"><span class="nav-icon-bullet"></span></span> 로그인 로그</a></li>
            <li class="nav-item"><a class="nav-link" href="#"><span class="nav-icon"><span class="nav-icon-bullet"></span></span> 탈퇴회원 로그</a></li>
            <li class="nav-item"><a class="nav-link" href="#"><span class="nav-icon"><span class="nav-icon-bullet"></span></span> 이메일 인증 로그</a></li>
            <li class="nav-item"><a class="nav-link" href="#"><span class="nav-icon"><span class="nav-icon-bullet"></span></span> 결제 로그</a></li>
            
          </ul>
        </li>
        
        <li class="nav-title">Extras</li>
        <li class="nav-group"><a class="nav-link nav-group-toggle" href="#">
            <svg class="nav-icon">
              <use xlink:href="${cp }/admin/img/free.svg#cil-star"></use>
            </svg> Pages</a>
          <ul class="nav-group-items compact">
            <li class="nav-item"><a class="nav-link" href="login.html" target="_top">
                <svg class="nav-icon">
                  <use xlink:href="${cp }/admin/img/free.svg#cil-account-logout"></use>
                </svg> Login</a></li>
            <li class="nav-item"><a class="nav-link" href="register.html" target="_top">
                <svg class="nav-icon">
                  <use xlink:href="${cp }/admin/img/free.svg#cil-account-logout"></use>
                </svg> Register</a></li>
            <li class="nav-item"><a class="nav-link" href="404.html" target="_top">
                <svg class="nav-icon">
                  <use xlink:href="${cp }/admin/img/free.svg#cil-bug"></use>
                </svg> Error 404</a></li>
            <li class="nav-item"><a class="nav-link" href="500.html" target="_top">
                <svg class="nav-icon">
                  <use xlink:href="${cp }/admin/img/free.svg#cil-bug"></use>
                </svg> Error 500</a></li>
          </ul>
        </li>
        <div class="accordion" id="accordionExample">
          <div class="accordion-item">
            <h2 class="accordion-header" id="headingOne">
              <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                Accordion Item #1
              </button>
            </h2>
            <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
              <div class="accordion-body">
                <strong>This is the first item's accordion body.</strong> It is shown by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It's also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
              </div>
            </div>
          </div>
          <div class="accordion-item">
            <h2 class="accordion-header" id="headingTwo">
              <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                Accordion Item #2
              </button>
            </h2>
            <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
              <div class="accordion-body">
                <strong>This is the second item's accordion body.</strong> It is hidden by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It's also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
              </div>
            </div>
          </div>
          <div class="accordion-item">
            <h2 class="accordion-header" id="headingThree">
              <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                Accordion Item #3
              </button>
            </h2>
            <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
              <div class="accordion-body">
                <strong>This is the third item's accordion body.</strong> It is hidden by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It's also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
              </div>
            </div>
          </div>
        </div>
      </ul>
      <!-- 사이드바 푸터 -->
      <div class="sidebar-footer border-top d-none d-md-flex">
        <button class="sidebar-toggler" type="button" data-coreui-toggle="unfoldable"></button>
      </div>
    </div>