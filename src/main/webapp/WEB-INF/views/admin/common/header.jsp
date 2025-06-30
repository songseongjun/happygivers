<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header class="header header-sticky p-0 mb-4">
        <div class="container-fluid border-bottom px-4">
          <button class="header-toggler" type="button" onclick="coreui.Sidebar.getInstance(document.querySelector('#sidebar')).toggle()" style="margin-inline-start: -14px;">
            <svg class="icon icon-lg">
              <use xlink:href="${cp }/admin/img/free.svg#cil-menu"></use>
            </svg>
          </button>
          <ul class="header-nav d-none d-lg-flex gap-5">
            <li class="nav-item"><a class="nav-link" href="#">홈</a></li>
            <li class="nav-item"><a class="nav-link" href="#">유저 목록</a></li>
            <li class="nav-item"><a class="nav-link" href="#">게시글 관리</a></li>
          </ul>
          
          <ul class="header-nav">
            <li class="nav-item dropdown"><a class="nav-link py-0 pe-0" data-coreui-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                <div class="avatar avatar-md"><img class="avatar-img" src="${cp }/admin/img/1.jpg" alt="user@email.com"></div>
              </a>
              <div class="dropdown-menu dropdown-menu-end pt-0">
                <div class="dropdown-header bg-body-tertiary text-body-secondary fw-semibold rounded-top mb-2">
                  <div class="fw-semibold">Settings</div>
                </div><a class="dropdown-item" href="#">
                  <svg class="icon me-2">
                    <use xlink:href="${cp }/admin/img/free.svg#cil-user"></use>
                  </svg> Profile</a>
                <div class="dropdown-divider"></div>
                <!-- <a class="dropdown-item" href="#">
                  <svg class="icon me-2">
                    <use xlink:href="img/free.svg#cil-account-logout"></use>
                  </svg> Login</a> -->
                <a class="dropdown-item" href="#">
                  <svg class="icon me-2">
                    <use xlink:href="${cp }/admin/img/free.svg#cil-account-logout"></use>
                  </svg> Logout</a>
              </div>
            </li>
          </ul>
        </div>

        <!-- 서브 네비게이션 바 -->
        <div class="container-fluid px-4">
          <nav aria-label="breadcrumb">
            <ol class="breadcrumb my-0">
              <li class="breadcrumb-item"><a href="#">홈</a></li>
              <li class="breadcrumb-item active"><span>2뎁스</span></li>
            </ol>
          </nav>
        </div>
      </header>