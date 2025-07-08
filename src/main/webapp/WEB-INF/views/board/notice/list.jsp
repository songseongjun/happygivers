<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/head.jsp" %>
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
<%@ include file="../../common/header.jsp" %>
	<div class="container">
        <main>
        	<div class="list-group">
                <div href="board _view.html" class="list-group-item">
                    <div class="row text-center align-items-center small text-black">
                        <span class="col-1 small">글 번호</span>
                        <span class="col-1 small">카테고리</span>
                        <span class="col text-start small">제목</span>
                        <span class="col-1 small">작성일</span>
                        <span class="col-1 small">조회수</span>
                    </div>
                </div>
                <c:forEach items="${boards}" var="board">
                <a href="view?bno=${board.bno}&${pageDto.cri.qs2}" class="list-group-item list-group-item-action">
                    <div class="row text-center align-items-center small text-muted">
                        <span class="col-1 small fw-bold">${board.bno}</span>
                        <span class="col-1 small">${board.cno}</span>
                        <span class="col text-start text-black">
                        <c:if test="${board.bno != board.grp}">
                        <i class="fa-solid fa-reply text-muted small" style="transform:rotate(180deg); margin-left: ${(board.depth - 2) * 16}px"></i>
                        </c:if>
                        ${board.title}<span class="small fw-bold text-danger ms-1">[${board.replyCnt }]</span> 
                        <c:if test="${board.attachCnt > 0}">
                        <i class="fa-solid fa-paperclip text-muted small ms-2"></i>
                        </c:if>
                        </span>
                        <span class="col-1 small"><span class="small">
                        <fmt:parseDate value="${board.regdate}" pattern="yyyy-MM-dd HH:mm:ss" var="parsedDate"/>
                        <fmt:formatDate value="${parsedDate}" pattern="yy.MM.dd"/>
                        </span></span>
                        <span class="col-1 small"><span class="small">${board.cnt}</span></span>
                    </div>
                </a>
                </c:forEach>
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

<%@ include file="../../common/footer.jsp" %>
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