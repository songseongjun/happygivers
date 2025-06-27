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
        
        <li class="nav-title">회원관리</li>
        <li class="nav-group"><a class="nav-link nav-group-toggle" href="#">
            <svg class="nav-icon">
              <use xlink:href="${cp }/admin/img/free.svg#cil-description"></use>
            </svg> 회원</a>
          <ul class="nav-group-items compact">
            <li class="nav-item"><a class="nav-link" href="#"><span class="nav-icon"><span class="nav-icon-bullet"></span></span> 회원목록 조회</a></li>
            <li class="nav-item"><a class="nav-link" href="#"><span class="nav-icon"><span class="nav-icon-bullet"></span></span> 기관회원 승인<span class="badge badge-sm bg-info ms-auto">NEW</span></a></li>
            <li class="nav-item"><a class="nav-link" href="#"><span class="nav-icon"><span class="nav-icon-bullet"></span></span> 서브관리자 생성</a></li>
          </ul>
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
      </ul>
      <!-- 사이드바 푸터 -->
      <div class="sidebar-footer border-top d-none d-md-flex">
        <button class="sidebar-toggler" type="button" data-coreui-toggle="unfoldable"></button>
      </div>
    </div>