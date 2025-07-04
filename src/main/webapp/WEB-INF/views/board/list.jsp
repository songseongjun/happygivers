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
<style type="text/css">
.tab {
  background-color: var(--col-1);
}
.active-tab {
  background-color: var(--col-main);
  color: #fff;
}
</style>
</head>

<body>
<%@ include file="../common/header.jsp" %>
	<div class="container">
        <main>
        	<c:if test="${not empty pageDto.cri.type}">
            <div class="search text-center">
            	
            	
                <h3>Search</h3>
                <p class="mb-5">'<span class="fw-semibold">${pageDto.cri.keyword }</span>'으로 검색하신 결과입니다</p>
            </div>
            </c:if>
            
            <c:if test="${empty pageDto.cri.type}">
            <div class="d-flex justify-content-around mx-5">
                <div >
                    <a href="${cp }/board/list"><img src="${cp }/img/menuicon/all.png" alt="all"><button class="btn btn-sm rounded-4 ms-2 ${pageDto.cri.cno == null ? 'active-tab' : 'tab'}">전체</button></a>                
                </div>
                <div >
                    <a href="${cp }/board/list?cno=3"><img src="${cp }/img/menuicon/kid.png" alt="all"><button class="btn btn-sm rounded-4 ms-2 ${pageDto.cri.cno == '3' ? 'active-tab' : 'tab'}">청소년</button></a>                
                </div>
                <div >
                    <a href="${cp }/board/list?cno=4"><img src="${cp }/img/menuicon/old.png" alt="all"><button class="btn btn-sm rounded-4 ms-2 ${pageDto.cri.cno == '4' ? 'active-tab' : 'tab'}" >어르신</button></a>                
                </div>
                <div>
                    <a href="${cp }/board/list?cno=5"><img src="${cp }/img/menuicon/animal.png" alt="all"><button class="btn btn-sm rounded-4 ms-2 ${pageDto.cri.cno == '5' ? 'active-tab' : 'tab'}" >동물</button></a>                
                </div>
                <div>
                    <a href="${cp }/board/list?cno=6"><img src="${cp }/img/menuicon/earth.png" alt="all"><button class="btn btn-sm rounded-4 ms-2 ${pageDto.cri.cno == '6' ? 'active-tab' : 'tab'}" >지구</button></a>                
                </div>
                <div>
                    <a href="${cp }/board/list?cno=7"><img src="${cp }/img/menuicon/area.png" alt="all"><button class="btn btn-sm rounded-4 ms-2 ${pageDto.cri.cno == '7' ? 'active-tab' : 'tab'}" >환경</button></a>                
                </div>
                <div>
                    <a href="${cp }/board/list?cno=8"><img src="${cp }/img/menuicon/needy.png" alt="all"><button class="btn btn-sm rounded-4 ms-2 ${pageDto.cri.cno == '8' ? 'active-tab' : 'tab'}" >장애인</button></a>                
                </div>
                <div>
                    <a href="${cp }/board/list?cno=9"><img src="${cp }/img/menuicon/social.png" alt="all"><button class="btn btn-sm rounded-4 ms-2 ${pageDto.cri.cno == '9' ? 'active-tab' : 'tab'}">사회</button></a>                
                </div>
            </div>
            </c:if>
            
             
            <div class="my-5">
                <div class="row row-cols-2 row-cols-md-3 row-cols-lg-4 g-4" id="boardList">
                     <!-- 포스트 -->
                   <c:if test="${not empty boards}">
					<c:forEach items="${boards }" var="b">
                     <div class="col">
		                <a href="${cp}/board/view?bno=${b.bno}" class="card post-card h-100 border-0 text-decoration-none">
		                	<div class="overflow-hidden rounded-3">
		                    	<img src="${b.thumbnail != null ? b.thumbnail : 'https://placehold.co/300x250?text=No+img' }" class="card-img-top img-zoom" alt="Post Image">
		                	</div>
		                    <div class="card-body d-flex flex-column px-0 pt-2">
		                        <p class="post-title my-2 fw-medium fs-6" style="color: var(--col-6);">${b.title }</p>
                                   <p class="post-meta mb-2 small" style="color: var(--col-3);">${b.name}</p>
		                        <div class="progress my-2" style="height: 3px;">
                                   <div class="progress-bar" style="width:70%"></div>
                                </div>
		                        <div class="progress-wrapper d-flex justify-content-between">
		                            <span><strong><fmt:formatNumber value="${b.round.nowamount}" />원</strong></span>
		                            <span class="text-primary">24%</span>
		                        </div>
								<div class="d-flex justify-content-between mt-2">
									<small class="text-muted ">23명 참여중</small>
									<small class="text-muted voidDate" data-voiddate="${b.round.voiddate }"></small>
								</div>
		                    </div>
		                </a>
		            </div>
		            </c:forEach>
		          </c:if>
                     
                </div>
            </div>
    
            <ul class="pagination my-5 justify-content-center">
            	
            	<c:if test="${pageDto.doubleLeft }">
				<li class="page-item"><a class="page-link" href="list?&page=1&${pageDto.cri.qs }"><i class="fa-solid fa-angles-left"></i></a></li>
            	</c:if>
				
				<c:if test="${pageDto.left }">
				<li class="page-item"><a class="page-link" href="list?&page=${pageDto.start - 1}&${pageDto.cri.qs}"><i class="fa-solid fa-angle-left"></i></a></li>
				</c:if>
				
				<c:forEach begin="${pageDto.start}" end="${pageDto.end}" var="i">
					<li class="page-item"><a class="page-link ${i == pageDto.cri.page ? 'active' : ''}" href="list?page=${i}&${pageDto.cri.qs}">${i}</a></li>
				</c:forEach>
				
				<c:if test="${pageDto.right }">
				<li class="page-item"><a class="page-link" href="list?page=${pageDto.end + 1}&${pageDto.cri.qs}"><i class="fa-solid fa-angle-right"></i></a></li>
				</c:if>
				
        		<c:if test="${pageDto.doubleRight }">
				<li class="page-item"><a class="page-link" href="list?page=${pageDto.realEnd}&${pageDto.cri.qs}"><i class="fa-solid fa-angles-right"></i></a></li>
				</c:if>
			</ul>
    
            <div class="search-bar">
                <form class="col input-group search-form">
                    <select class="form-select form-select-sm" name="type">
                        <option value="TCN">전체</option>
                        <option value="T">제목</option>
                        <option value="C">내용</option>
                        <option value="N">작성자</option>
                    </select>
                    <input type="text" class="form-control form-control-sm w-75" placeholder="Search" name="keyword">
                    <input type="hidden" name="page" value="1">
                    <input type="hidden" name="amount" value="${pageDto.cri.amount}">
                    <input type="hidden" name="cno" value="${pageDto.cri.cno}">
                    <button class="btn btn-dark btn-sm" type="submit">검색</button>
                </form>
                <script type="text/javascript">
            		$(".search-form").submit(function() {
            			event.preventDefault();
            			this.keyword.value = encodeURIComponent(this.keyword.value);
						this.submit();          			
            		})
            	</script>
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
</script>
</body>
</html>