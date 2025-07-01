<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header class="header header-sticky p-0 mb-4">
        <div class="container-fluid border-bottom px-4">
          <ul class="header-nav ms-auto">
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
      </header>