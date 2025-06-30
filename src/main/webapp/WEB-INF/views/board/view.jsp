<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/dayjs/1.11.13/dayjs.min.js" integrity="sha512-FwNWaxyfy2XlEINoSnZh1JQ5TRRtGow0D6XcmAWmYCRgvqOUTnzCxPc9uF35u5ZEpirk1uhlPVA19tflhvnW1g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/dayjs/1.11.13/locale/ko.min.js" integrity="sha512-ycjm4Ytoo3TvmzHEuGNgNJYSFHgsw/TkiPrGvXXkR6KARyzuEpwDbIfrvdf6DwXm+b1Y+fx6mo25tBr1Icg7Fw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/dayjs/1.11.13/plugin/relativeTime.min.js" integrity="sha512-MVzDPmm7QZ8PhEiqJXKz/zw2HJuv61waxb8XXuZMMs9b+an3LoqOqhOEt5Nq3LY1e4Ipbbd/e+AWgERdHlVgaA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<style>
.toastui-editor-contents *{
	margin: 1.5rem 0 !important;
}

.toastui-editor-contents > *:first-child{
	margin: 0 !important;
}
.toastui-editor-contents p {
  font-size: 18px; /* 원하는 글씨 크기로 조정 */
}
.toastui-editor-contents img {
	display: block;
	width: 100%;
	height: auto;
	object-fit: cover;
	border-radius: 1rem;
}
</style>
</head>

<body>
<%@ include file="../common/header.jsp" %>
	<div class="container px-0">
        <main class="row justify-content-between mx-0">
            <div class="col-lg-8 px-0" style="max-width: 700px;">
               
                <!-- 컨텐츠 -->
                <div id="viewer" class="pb-4"></div>
                

                <!-- 댓글 -->
                <div>
                    <div class="d-flex justify-content-between align-items-center mt-5">
                        <p class="fs-5 fw-semibold m-0">댓글<span class="small"> 25</span></p>
                        <button class="btn btn-outline-primary">댓글 작성</button>
                    </div>
                    <hr>
                    <ul class="m-0 p-0 d-flex flex-column gap-4">
                        <!-- 댓글 한개 -->
                        <li class="d-flex gap-3">
                            <div class="rounded-5 overflow-hidden border-2" style="border: 2px solid var(--border-1); width: 40px; height: 40px; box-sizing: border-box;"><img src="img/img1.png" alt="프로필 사진" style="width: 40px; height: 40px; object-fit: cover;" ></div>
                            <div class="flex-grow-1">
                                <div class="d-flex justify-content-between my-2">
                                    <p class="fw-medium m-0" style="color: var(--col-3);"><span style="color: var(--col-6);">키다리아저씨</span>님</p>
                                    <p class="small text-muted m-0">상위 5%</p>
                                </div>
                                <div class="p-3 rounded-3 mt-3" style="background-color: var(--col-1);">
                                    <p class="m-0 small">응원합니다.</p>
                                </div>
                                <div class="small d-flex justify-content-end gap-3 mt-2">
                                    <label><i class="fa-regular fa-heart me-1"></i> 좋아요<button class="d-none"></button></label>
                                    <span>3분 전</span>
                                    <label class="text-decoration-underline" style="color: var(--col-3);">수정<button class="d-none"></button></label>
                                    <label class="text-decoration-underline" style="color: var(--col-3);">삭제<button class="d-none"></button></label>
                                </div>
                            </div>
                        </li>
                        <!-- 댓글 한개 -->
                        <li class="d-flex gap-3">
                            <div class="rounded-5 overflow-hidden border-2" style="border: 2px solid var(--border-2); width: 40px; height: 40px; box-sizing: border-box;"><img src="img/img1.png" alt="프로필 사진" style="width: 40px; height: 40px; object-fit: cover;" ></div>
                            <div class="flex-grow-1">
                                <div class="d-flex justify-content-between my-2">
                                    <p class="fw-medium m-0" style="color: var(--col-3);"><span style="color: var(--col-6);">키다리아저씨</span>님</p>
                                    <p class="small text-muted m-0">상위 5%</p>
                                </div>
                                <div class="p-3 rounded-3 mt-3" style="background-color: var(--col-1);">
                                    <p class="m-0 small">응원합니다.</p>
                                </div>
                                <div class="small d-flex justify-content-end gap-3 mt-2">
                                    <label><i class="fa-regular fa-heart me-1"></i> 좋아요<button class="d-none"></button></label>
                                    <span>3분 전</span>
                                    <label class="text-decoration-underline" style="color: var(--col-3);">수정<button class="d-none"></button></label>
                                    <label class="text-decoration-underline" style="color: var(--col-3);">삭제<button class="d-none"></button></label>
                                </div>
                            </div>
                        </li>
                        <!-- 댓글 한개 -->
                        <li class="d-flex gap-3">
                            <div class="rounded-5 overflow-hidden border-2" style="border: 2px solid var(--border-3); width: 40px; height: 40px; box-sizing: border-box;"><img src="img/img1.png" alt="프로필 사진" style="width: 40px; height: 40px; object-fit: cover;" ></div>
                            <div class="flex-grow-1">
                                <div class="d-flex justify-content-between my-2">
                                    <p class="fw-medium m-0" style="color: var(--col-3);"><span style="color: var(--col-6);">키다리아저씨</span>님</p>
                                    <p class="small text-muted m-0">상위 5%</p>
                                </div>
                                <div class="p-3 rounded-3 mt-3" style="background-color: var(--col-1);">
                                    <p class="m-0 small">응원합니다.</p>
                                </div>
                                <div class="small d-flex justify-content-end gap-3 mt-2">
                                    <label><i class="fa-regular fa-heart me-1"></i> 좋아요<button class="d-none"></button></label>
                                    <span>3분 전</span>
                                    <label class="text-decoration-underline" style="color: var(--col-3);">수정<button class="d-none"></button></label>
                                    <label class="text-decoration-underline" style="color: var(--col-3);">삭제<button class="d-none"></button></label>
                                </div>
                            </div>
                        </li>
                    </ul>
                    <div class="d-flex justify-content-center mt-4">
                        <button class="btn btn-outline-secondary rounded-5 px-4">댓글 더보기 <i class="fa-solid fa-chevron-down small ms-1" style="color: var(--col-3);"></i></button>
                    </div>
                </div>

                
            </div>
            <div class="col-lg-4 px-0" style="max-width: 360px;">
                <!-- 제목, 모금 정보 -->
                <div>
                    <div class="d-flex flex-column justify-content-between flex-grow-1">
                        <div class="card-body">
                            <p class="fs-3 fw-semibold mb-1">${board.title }</p>
                            <p class="post-meta my-3 fs-5 fw-bold text-end" style="color: var(--col-6);">2,752,046원</p>
                            
                            <div class="progress my-3" style="height: 5px;">
                                <div class="progress-bar" style="width:70%"></div>
                            </div>
                            <div class="progress-wrapper d-flex justify-content-between mb-4">
                                <span class="text-primary ">65% 달성</span>
                                <span><strong><fmt:formatNumber value="${round.goalamount}" />원 목표</strong></span>
                            </div>
                        </div>
                        <a href="#" class="btn btn-primary form-control py-2" >기부하기</a>
                    </div> 
                </div>


                <!-- 모금함 기부정보 -->
                <div class="border rounded-3 mt-4">
                    <div class="p-4">
                        <div class="d-flex justify-content-between">
                            <p class="fs-6 fw-semibold" style="color: var(--col-6);">모금함 기부 현황</p>
                            <p class="small" style="color: var(--col-4);">23<span>명 참여</span></p>
                        </div>
                        <div class="rounded-3 py-2" style="background-color: var(--col-1);">
                            <p class="text-center fw-semibold fs-5 m-0">24,242,242,242<span>원</span></p>
                        </div>
                    </div>
                    <div class="p-4" style="background-color: var(--col-1);">
                        <div class="">
                            <p class="fs-6 fw-semibold" style="color: var(--col-6);">내 기부금</p>
                        </div>
                        <div class="rounded-3 py-2" style="background-color: var(--col-0);" >
                            <p class="text-center fw-semibold fs-5 m-0">10,000<span>원</span></p>
                        </div>
                    </div>
                </div>
                
                <div class="border rounded-3 mt-4">
                    <div class="p-4">
                        <div class="">
                            <p class="fs-6 fw-semibold" style="color: var(--col-6);">이 모금함의 우수 후원자</p>
                        </div>
                        <ul class="m-0 p-0 d-flex flex-column gap-2">
                            <li class="d-flex align-items-center gap-3 rounded-2 p-2" style="background-color: var(--bg-1);">
                                <img src="img/img1.png" class="rounded-5" alt="프로필 사진" style="width: 40px; height: 40px; object-fit: cover;">
                                <p class="m-0 flex-grow-1 fw-medium"><span>키다리아저씨</span>님</p>
                                <p class="m-0 small" style="color: var(--col-3);">상위 24%</p>
                            </li>
                            <li class="d-flex align-items-center gap-3 rounded-2 p-2" style="background-color: var(--bg-2);">
                                <img src="img/img1.png" class="rounded-5" alt="프로필 사진" style="width: 40px; height: 40px; object-fit: cover;">
                                <p class="m-0 flex-grow-1 fw-medium"><span>키다리아저씨</span>님</p>
                                <p class="m-0 small" style="color: var(--col-3);">상위 56%</p>
                            </li>
                            <li class="d-flex align-items-center gap-3 rounded-2 p-2" style="background-color: var(--bg-3);">
                                <img src="img/img1.png" class="rounded-5" alt="프로필 사진" style="width: 40px; height: 40px; object-fit: cover;">
                                <p class="m-0 flex-grow-1 fw-medium"><span>키다리아저씨</span>님</p>
                                <p class="m-0 small" style="color: var(--col-3);">상위 78%</p>
                            </li>
                        </ul>
                    </div>
                    <div class="p-2" style="background-color: var(--col-1);">
                        <div class="d-flex justify-content-center">
                            <a href="#" class="fs-6 m-0 py-1 text-decoration-none" style="color: var(--col-4);">후원자 전체 목록 보기 <i class="fa-solid fa-chevron-right small" style="color: var(--col-4);"></i></a>
                        </div>
                    </div>
                </div>

                <div class="border rounded-3 mt-4">
                    <div class="p-4">
                        <div class="d-flex">
                            <div class="rounded-5 overflow-hidden" style="border:2px solid var(--col-main);">
                                <img src="img/img1.png" alt="" style="width: 40px; height: 40px; object-fit: cover;">
                            </div>
                            <div class="ms-3">
                                <p class="fs-6 fw-semibold m-0" style="color: var(--col-6);">사회복지법인 아산성애원 좋은이웃</p>
                                <p class="small m-0" style="color: var(--col-3);">후원기관</p>
                            </div>
                        </div>
                        <p class="small mt-3 mb-0">"함께 성장하며 희망을 나누는 곳" 정신건강의 어려움을
겪는 분들이 지역사회의 구성원으로 희망을 가지고 존중
받으며 살아가도록 지원하는 정신재활시설 좋은이웃…</p>
                        
                    </div>
                    <div class="p-2" style="background-color: var(--col-1);">
                        <div class="d-flex justify-content-center">
                            <a href="#" class="fs-6 m-0 py-1 text-decoration-none" style="color: var(--col-4);">자세히보기 <i class="fa-solid fa-chevron-right small" style="color: var(--col-4);"></i></a>
                        </div>
                    </div>
                </div>



                <!-- 해당 후원기관의 다른 모금함 -->
                <div class="border rounded-3 mt-4 p-4">
                    <div class="">
                        <p class="fs-6 fw-semibold m-0" style="color: var(--col-6);">이 후원기관의 다른 모금함</p>
                    </div>
                    <hr>
                    <ul class="p-0 d-flex flex-column gap-3 m-0
                    ">
                        <li>
                            <a href="#" class="d-flex text-decoration-none">
                                <img src="img/img4.png" class="rounded-2" alt="이미지4" style="height: 68px; width: 100px; object-fit: cover;">
                                <div class="ms-3 small">
                                    <p>혼자가 아닌 함께, 복날 삼계탕 나눔잔치</p>
                                    <p class="m-0 small" style="color: var(--col-4);">임실군노인복지관</p>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#" class="d-flex text-decoration-none">
                                <img src="img/img4.png" class="rounded-2" alt="이미지4" style="height: 68px; width: 100px; object-fit: cover;">
                                <div class="ms-3 small">
                                    <p>혼자가 아닌 함께, 복날 삼계탕 나눔잔치</p>
                                    <p class="m-0 small" style="color: var(--col-4);">임실군노인복지관</p>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#" class="d-flex text-decoration-none">
                                <img src="img/img4.png" class="rounded-2" alt="이미지4" style="height: 68px; width: 100px; object-fit: cover;">
                                <div class="ms-3 small">
                                    <p>혼자가 아닌 함께, 복날 삼계탕 나눔잔치</p>
                                    <p class="m-0 small" style="color: var(--col-4);">임실군노인복지관</p>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#" class="d-flex text-decoration-none">
                                <img src="img/img4.png" class="rounded-2" alt="이미지4" style="height: 68px; width: 100px; object-fit: cover;">
                                <div class="ms-3 small">
                                    <p>혼자가 아닌 함께, 복날 삼계탕 나눔잔치</p>
                                    <p class="m-0 small" style="color: var(--col-4);">임실군노인복지관</p>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#" class="d-flex text-decoration-none">
                                <img src="img/img4.png" class="rounded-2" alt="이미지4" style="height: 68px; width: 100px; object-fit: cover;">
                                <div class="ms-3 small">
                                    <p>혼자가 아닌 함께, 복날 삼계탕 나눔잔치</p>
                                    <p class="m-0 small" style="color: var(--col-4);">임실군노인복지관</p>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#" class="d-flex text-decoration-none">
                                <img src="img/img4.png" class="rounded-2" alt="이미지4" style="height: 68px; width: 100px; object-fit: cover;">
                                <div class="ms-3 small">
                                    <p>혼자가 아닌 함께, 복날 삼계탕 나눔잔치</p>
                                    <p class="m-0 small" style="color: var(--col-4);">임실군노인복지관</p>
                                </div>
                            </a>
                        </li>
                    </ul>
                </div>
                
            </div>
        </main>
    </div>

<%@ include file="../common/footer.jsp" %>
<script type="text/javascript">
	dayjs.extend(window.dayjs_plugin_relativeTime);
	dayjs.locale('ko');
	const dayForm = 'YYYY-MM-DD HH:mm:ss';
	
	$("*[data-voiddate]").each((v,i) => {
		const date = $(i).data("voiddate");
		const deadlineText = dayjs(date, dayForm).fromNow();
		$(i).text(deadlineText + " 마감");
	})


	const content = `${board.content}`.replaceAll("\\n", "\n").replaceAll("\\\"", "\"");
  const viewer = new toastui.Editor.factory({
    el: document.querySelector('#viewer'),
    viewer: true,
    initialValue: content
  });

</script>
</body>
</html>