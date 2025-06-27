<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/head.jsp" %>
</head>
<body>
<%@ include file="common/header.jsp" %>
	<div class="container px-0">
        <main class="row justify-content-between mx-0">
            <div class="col-lg-8 px-0" style="max-width: 700px;">
                <!-- 슬라이더 -->
                <div class="swiper rounded-4">
                    <div class="swiper-wrapper">
                        <div class="swiper-slide">
                            <a href="#"><img src="${cp }/img/mainbanner.png" alt="메인배너1"></a>
                        </div>
                        <div class="swiper-slide">
                            <a href="#"><img src="${cp }/img/mainbanner.png" alt="메인배너2"></a>
                        </div>
                        <div class="swiper-slide">
                            <a href="#"><img src="${cp }/img/mainbanner.png" alt="메인배너3"></a>
                        </div>
                    </div>
                    <div class="swiper-pagination"></div>
                </div>
                <!-- 메뉴 -->
                <div class="border p-4 rounded-4 mt-4">
                    <p class="fs-5 fw-semibold">나의 도움이 필요한 후원처</p>
                    <ul class="d-flex px-0 m-0">
                        <li style="width: 12.5%;">
                            <a href="#" class="text-decoration-none d-flex flex-column align-items-center">
                                <img src="${cp }/img/menuicon/all.png" style="width: 32px;" alt="전체">
                                <p class="d-block py-1 m-0 rounded-5 text-center mt-2" style="background-color: var(--col-1); color: var(--col-6); min-width: 60px;">전체</p>
                            </a>
                        </li>
                        <li style="width: 12.5%;">
                            <a href="#" class="text-decoration-none d-flex flex-column align-items-center">
                                <img src="${cp }/img/menuicon/kid.png" style="width: 32px;" alt="전체">
                                <p class="d-block py-1 m-0 rounded-5 text-center mt-2" style="background-color: var(--col-1); color: var(--col-6); min-width: 60px;">청소년</p>
                            </a>
                        </li>
                        <li style="width: 12.5%;">
                            <a href="#" class="text-decoration-none d-flex flex-column align-items-center">
                                <img src="${cp }/img/menuicon/old.png" style="width: 32px;" alt="전체">
                                <p class="d-block py-1 m-0 rounded-5 text-center mt-2" style="background-color: var(--col-1); color: var(--col-6); min-width: 60px;">어르신</p>
                            </a>
                        </li>
                        <li style="width: 12.5%;">
                            <a href="#" class="text-decoration-none d-flex flex-column align-items-center">
                                <img src="${cp }/img/menuicon/animal.png" style="width: 32px;" alt="전체">
                                <p class="d-block py-1 m-0 rounded-5 text-center mt-2" style="background-color: var(--col-1); color: var(--col-6); min-width: 60px;">동물</p>
                            </a>
                        </li>
                        <li style="width: 12.5%;">
                            <a href="#" class="text-decoration-none d-flex flex-column align-items-center">
                                <img src="${cp }/img/menuicon/earth.png" style="width: 32px;" alt="전체">
                                <p class="d-block py-1 m-0 rounded-5 text-center mt-2" style="background-color: var(--col-1); color: var(--col-6); min-width: 60px;">지구</p>
                            </a>
                        </li>
                        <li style="width: 12.5%;">
                            <a href="#" class="text-decoration-none d-flex flex-column align-items-center">
                                <img src="${cp }/img/menuicon/area.png" style="width: 32px;" alt="전체">
                                <p class="d-block py-1 m-0 rounded-5 text-center mt-2" style="background-color: var(--col-1); color: var(--col-6); min-width: 60px;">환경</p>
                            </a>
                        </li>
                        <li style="width: 12.5%;">
                            <a href="#" class="text-decoration-none d-flex flex-column align-items-center">
                                <img src="${cp }/img/menuicon/needy.png" style="width: 32px;" alt="전체">
                                <p class="d-block py-1 m-0 rounded-5 text-center mt-2" style="background-color: var(--col-1); color: var(--col-6); min-width: 60px;">장애인</p>
                            </a>
                        </li>
                        <li style="width: 12.5%;">
                            <a href="#" class="text-decoration-none d-flex flex-column align-items-center">
                                <img src="${cp }/img/menuicon/social.png" style="width: 32px;" alt="전체">
                                <p class="d-block py-1 m-0 rounded-5 text-center mt-2" style="background-color: var(--col-1); color: var(--col-6); min-width: 60px;">사회</p>
                            </a>
                        </li>
                    </ul>
                </div>
                <!-- 컨텐츠 1 -->
                <div class="border p-4 rounded-4 mt-4">
                    <p class="fs-5 fw-semibold mb-1">소중한 기부자를 찾고 있어요.</p>
                    <p class="fs-6 small" style="color: var(--col-3);">목표 달성까지 얼마 남지 않았어요!</p>
                    <div class="d-flex mt-4">
                        <div>
                            <div class="d-flex justify-content-between rounded-top-3" style="background-color: var(--col-6);">
                                <p class="m-2" style="color: var(--col-2);">남은 금액</p>
                                <p class="text-white m-2">344,343<span class="ms-2" style="color: var(--col-2);">원</span></p>
                            </div>
                            <div>
                                <img src="${cp }/img/mainimg1.png" class="rounded-bottom-3" alt="img1">
                            </div>
                        </div>
                        <div class="d-flex flex-column justify-content-between ms-4 flex-grow-1">
                            <div class="card-body">
                                <p class="post-title mb-1 fw-semibold fs-6">학대피해아동 소망이에게 따뜻함을 알려주세요</p>
                                <p class="post-meta mb-2 small" style="color: var(--col-3);">한국고양이보호협회</p>
                                <div class="progress my-3" style="height: 5px;">
                                    <div class="progress-bar" style="width:70%"></div>
                                </div>
                                <div class="progress-wrapper d-flex justify-content-between mt-1">
                                    <span><strong>2,752,060원</strong></span>
                                    <span class="text-primary">65%</span>
                                </div>
                            </div>
                            <a href="#" class="btn btn-outline-light form-control" style="color: var(--col-3); border-color: var(--col-2);">기부하기</a>
                        </div>
                    </div>
                </div>
                <!-- 컨텐츠 2 -->
                <div class="border p-4 rounded-4 mt-4">
                    <p class="fs-5 fw-semibold mb-1">오늘 많이 기부중인 모금함</p>
                    <p class="fs-6 small" style="color: var(--col-3);">조금만 힘을 보태주세요.</p>
                    <ul class="px-0 d-flex justify-content-between mb-0">
                        <li style="width: 31%;">
                            <a href="#" class="text-decoration-none">
                                <div class="card-body">
                                    <img src="${cp }/img/img1.png" class="rounded-3" alt="이미지1">
                                    <p class="post-title my-2 fw-medium fs-6" style="color: var(--col-6);">평생가족을 기다리는 친구들 입양 준비를 함께해 주세요!</p>
                                    <p class="post-meta mb-2 small" style="color: var(--col-3);">한국고양이보호협회</p>
                                    <div class="progress my-2" style="height: 3px;">
                                        <div class="progress-bar" style="width:70%"></div>
                                    </div>
                                    <div class="progress-wrapper d-flex justify-content-between mt-1">
                                        <span style="color: var(--col-6);"><strong>2,752,060원</strong></span>
                                        <span class="text-primary">65%</span>
                                </div>
                            </div>
                            </a>
                        </li>
                        <li style="width: 31%;">
                            <a href="#" class="text-decoration-none">
                                <div class="card-body">
                                    <img src="${cp }/img/img2.png" class="rounded-3" alt="이미지1">
                                    <p class="post-title my-2 fw-medium fs-6" style="color: var(--col-6);">평생가족을 기다리는 친구들 입양 준비를 함께해 주세요!</p>
                                    <p class="post-meta mb-2 small" style="color: var(--col-3);">한국고양이보호협회</p>
                                    <div class="progress my-2" style="height: 3px;">
                                        <div class="progress-bar" style="width:70%"></div>
                                    </div>
                                    <div class="progress-wrapper d-flex justify-content-between mt-1">
                                        <span style="color: var(--col-6);"><strong>2,752,060원</strong></span>
                                        <span class="text-primary">65%</span>
                                </div>
                            </div>
                            </a>
                        </li>
                        <li style="width: 31%;">
                            <a href="#" class="text-decoration-none">
                                <div class="card-body">
                                    <img src="${cp }/img/img3.png" class="rounded-3" alt="이미지1">
                                    <p class="post-title my-2 fw-medium fs-6" style="color: var(--col-6);">평생가족을 기다리는 친구들 입양 준비를 함께해 주세요!</p>
                                    <p class="post-meta mb-2 small" style="color: var(--col-3);">한국고양이보호협회</p>
                                    <div class="progress my-2" style="height: 3px;">
                                        <div class="progress-bar" style="width:70%"></div>
                                    </div>
                                    <div class="progress-wrapper d-flex justify-content-between mt-1">
                                        <span style="color: var(--col-6);"><strong>2,752,060원</strong></span>
                                        <span class="text-primary">65%</span>
                                </div>
                            </div>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-4 px-0" style="max-width: 360px;">
                <!-- 로그인 전-->
                <div class="border p-4 rounded-3">
                    <p class="text-center" style="color: var(--col-5);">당신의 소중한 후원을 기다립니다.</p>
                    <a href="#" class="btn btn-primary form-control py-2 fs-6">로그인</a>
                    <ul class="d-flex gap-3 mt-3 mb-0 mx-auto px-0 justify-content-center small">
                        <li><a href="#" class="text-decoration-none" style="color: var(--col-5);">아이디 찾기</a></li>
                        <li><a href="#" class="text-decoration-none" style="color: var(--col-5);">비밀번호 찾기</a></li>
                        <li><a href="#" class="text-decoration-none" style="color: var(--col-5);">회원가입</a></li>
                    </ul>
                </div>

                <!-- 로그인 후-->
                <!-- <div class="border rounded-3">
                    <div class="p-4 d-flex align-items-center gap-3 border-bottom">
                        <div class="overflow-hidden rounded-circle" style="width: 64px; height: 64px; border: 2px solid var(--border-1);">
                            <img src="${cp }/img/img1.png" class="object-fit-cover" alt="프로필사진" style="width: 64px; height: 64px;">
                        </div>
                        <p class="fw-semibold fs-6 mb-0 flex-grow-1 text-truncate" style="color: var(--col-4);"><span style="color: var(--col-6);">키다리아저씨</span> 님</p>
                        <a href="#" class="text-decoration-none border px-3 py-1 rounded-5 small" style="color: var(--col-3);">로그아웃</a>
                    </div>
                    <div class="p-3" style="background-color: var(--col-1);">
                        <div class="d-flex justify-content-around">
                            <a href="#" class="text-decoration-none fw-medium small" style="color: var(--col-5);">프로필 수정</a>
                            <a href="#" class="text-decoration-none fw-medium small" style="color: var(--col-5);">마이페이지</a>
                        </div>
                    </div>
                </div> -->

                <!-- 기부금 -->
                <div class="border rounded-3 mt-4">
                    <div class="p-4">
                        <div class="d-flex justify-content-between">
                            <p class="fs-6 fw-semibold" style="color: var(--col-6);">전체 기부금</p>
                            <p class="small" style="color: var(--col-4);">2025.06.20 <span>기준</span></p>
                        </div>
                        <div class="rounded-3 py-2" style="background-color: var(--col-1);">
                            <p class="text-center fw-semibold fs-5 m-0">24,242,242,242<span>원</span></p>
                        </div>
                    </div>
                    <div class="p-4" style="background-color: var(--col-1);">
                        <div class="d-flex justify-content-between">
                            <p class="fs-6 fw-semibold" style="color: var(--col-6);">내 기부금</p>
                            <p class="small" style="color: var(--col-4);">2025.06.20 <span>기준</span></p>
                        </div>
                        <div class="rounded-3 py-2" style="background-color: var(--col-0);" >
                            <p class="text-center fw-semibold fs-5 m-0">10,000<span>원</span></p>
                        </div>
                    </div>
                    
                    
                </div>
                <!-- 후원처 -->
                <!-- <div class="border rounded-3 mt-4 p-4">
                    <div class="d-flex justify-content-between">
                        <p class="fs-6 fw-semibold m-0" style="color: var(--col-6);">현재 등록된 내 모금함</p>
                        <a class="small text-decoration-none" style="color: var(--col-4);">더보기 <i class="fa-solid fa-chevron-right" style="color: var(--col-4);"></i></a>
                    </div>
                    <hr>
                    <ul class="p-0 d-flex flex-column gap-3 m-0
                    ">
                        <li>
                            <a href="#" class="d-flex text-decoration-none">
                                <img src="${cp }/img/img4.png" class="rounded-3" alt="이미지4">
                                <div class="ms-3">
                                    <p>혼자가 아닌 함께, 복날 삼계탕 나눔잔치</p>
                                    <p class="m-0 small" style="color: var(--col-4);">임실군노인복지관</p>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#" class="d-flex text-decoration-none">
                                <img src="${cp }/img/img4.png" class="rounded-3" alt="이미지4">
                                <div class="ms-3">
                                    <p>혼자가 아닌 함께, 복날 삼계탕 나눔잔치</p>
                                    <p class="m-0 small" style="color: var(--col-4);">임실군노인복지관</p>
                                </div>
                            </a>
                        </li>
                    </ul>
                    <a href="#" class="btn btn-primary form-control fs-6 mt-4 py-2">모금 글 작성하기</a>
                </div> -->
                <!-- 공지사항 -->
                <div class="border rounded-3 mt-4 p-4">
                    <div class="d-flex justify-content-between">
                        <p class="fs-6 fw-semibold m-0" style="color: var(--col-6);">공지사항</p>
                        <a class="small text-decoration-none" style="color: var(--col-4);">더보기 <i class="fa-solid fa-chevron-right" style="color: var(--col-4);"></i></a>
                    </div>
                    <hr>
                    <ul class="p-0 d-flex flex-column m-0">
                        <li><a href="#" class="text-decoration-none text-truncate py-3 border-bottom d-block" style="color: var(--col-6);">공지사항 입니다 공지사항 입니다. 공지사항 입니다 공지사항 입니다</a></li>
                        <li><a href="#" class="text-decoration-none text-truncate py-3 border-bottom d-block" style="color: var(--col-6);">공지사항 입니다 공지사항 입니다. 공지사항 입니다 공지사항 입니다</a></li>
                        <li><a href="#" class="text-decoration-none text-truncate py-3 d-block" style="color: var(--col-6);">공지사항 입니다 공지사항 입니다. 공지사항 입니다 공지사항 입니다</a></li>
                    </ul>
                </div>
                <!-- Q&A -->
                <div class="border rounded-3 mt-4 p-4">
                    <div class="d-flex justify-content-between">
                        <p class="fs-6 fw-semibold m-0" style="color: var(--col-6);">Q&A</p>
                        <a class="small text-decoration-none" style="color: var(--col-4);">더보기 <i class="fa-solid fa-chevron-right" style="color: var(--col-4);"></i></a>
                    </div>
                    <hr>
                    <ul class="p-0 d-flex flex-column m-0">
                        <li><a href="#" class="text-decoration-none text-truncate py-3 border-bottom d-block" style="color: var(--col-6);">QNAafaskdjhfkljahdsghfsadsdafsdfasdfQNAafaskdjhfkljahdsghfsadsdafsdfasdf</a></li>
                        <li><a href="#" class="text-decoration-none text-truncate py-3 border-bottom d-block" style="color: var(--col-6);">QNAafaskdjhfkljahdsghfsadsdafsdfasdfQNAafaskdjhfkljahdsghfsadsdafsdfasdf</a></li>
                        <li><a href="#" class="text-decoration-none text-truncate py-3 d-block" style="color: var(--col-6);">QNAafaskdjhfkljahdsghfsadsdafsdfasdfQNAafaskdjhfkljahdsghfsadsdafsdfasdf</a></li>
                    </ul>
                </div>
            </div>
        </main>
    </div>
<%@ include file="common/footer.jsp" %>
<script>
        const swiper = new Swiper('.swiper', {
        // Optional parameters
        // direction: 'vertical',
        loop: true,

        // If we need pagination
        pagination: {
            el: '.swiper-pagination',
        },
    });
    </script>
</body>
</html>