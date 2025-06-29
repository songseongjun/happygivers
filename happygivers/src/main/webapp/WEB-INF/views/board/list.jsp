<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp" %>
</head>
<body>
<%@ include file="../common/header.jsp" %>
	<div class="container">
        <main>
            <div class="search text-center">
                <h3>Search</h3>
                <p class="mb-5">'<span class="fw-semibold">CATEGORY</span>'으로 검색하신 결과입니다</p>
            </div>
            <!-- Category List Start -->
            <div class="d-flex justify-content-around mx-5">
                <div >
                    <a href="Feed_Category_Main.html"><img src="img/menuicon/all.png" alt="all"><button class="btn btn-sm rounded-4" style="background-color: var(--col-main); color: #fff;">전체</button></a>                
                </div>
                <div >
                    <a href="Feed_Category_Teen.html"><img src="img/menuicon/kid.png" alt="all"><button class="btn btn-sm rounded-4" style="background-color: var(--col-1);">청소년</button></a>                
                </div>
                <div >
                    <a href="Feed_Category_Old.html"><img src="img/menuicon/old.png" alt="all"><button class="btn btn-sm rounded-4" style="background-color: var(--col-1);">어르신</button></a>                
                </div>
                <div>
                    <a href="Feed_Category_Animal.html"><img src="img/menuicon/animal.png" alt="all"><button class="btn btn-sm rounded-4" style="background-color: var(--col-1);">동물</button></a>                
                </div>
                <div>
                    <a href="Feed_Category_Earth.html"><img src="img/menuicon/earth.png" alt="all"><button class="btn btn-sm rounded-4" style="background-color: var(--col-1);">지구</button></a>                
                </div>
                <div>
                    <a href="Feed_Category_Area.html"><img src="img/menuicon/area.png" alt="all"><button class="btn btn-sm rounded-4" style="background-color: var(--col-1);">환경</button></a>                
                </div>
                <div>
                    <a href="Feed_Category_Needy.html"><img src="img/menuicon/needy.png" alt="all"><button class="btn btn-sm rounded-4" style="background-color: var(--col-1);">장애인</button></a>                
                </div>
                <div>
                    <a href="Feed_Category_Social.html"><img src="img/menuicon/social.png" alt="all"><button class="btn btn-sm rounded-4" style="background-color: var(--col-1);">사회</button></a>                
                </div>
            </div>
            <!-- Category List End -->
             
            <div class="container my-5">
                <div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 g-4" id="post-grid">
                    <!-- Dynamic tiles will be injected here -->
                </div>
            </div>
    
            <!-- Pagination -->
            <div id="pagination" class="d-flex justify-content-center my-3 pb-4"></div>
    
            <div class="search-bar">
                <form class="col input-group search-form">
                    <select class="form-select form-select-sm" name="type">
                        <option value="T">제목</option>
                        <option value="C">내용</option>
                        <option value="I">작성자</option>
                        <option value="TC">제목+내용</option>
                        <option value="TI">제목+작성자</option>
                        <option value="CI">내용+작성자</option>
                        <option value="TCI">제목+내용+작성자</option>
                    </select>
                    <input type="text" class="form-control form-control-sm w-75" placeholder="Search" name="keyword">
                    <input type="hidden" name="page" value="1">
                    <input type="hidden" name="amount" value="${pageDto.cri.amount}">
                    <input type="hidden" name="cno" value="${pageDto.cri.cno}">
                    <button class="btn btn-dark btn-sm" type="submit">검색</button>
                </form>
            </div>
        </main>

    </div>

<%@ include file="../common/footer.jsp" %>
	
</body>
</html>