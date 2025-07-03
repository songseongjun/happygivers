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
        <h2 class="text-center my-5">기부 글 수정</h2>
        <form class="my-4" id="writeForm" method="post">
          <div class="form-control d-flex gap-2 mb-2">
            <div>
              <select class="form-select" style="min-width: 200px;" name="cno">
                <option value="3">청소년</option>
                <option value="4">어르신</option>
                <option value="5">동물</option>
                <option value="6">지구</option>
                <option value="7">환경</option>
                <option value="8">장애인</option>
                <option value="9">사회</option>
              </select>
            </div>
            <input type="text" class="form-control" placeholder="제목" name="title" value="${board.title }" required>
          </div>
          <div id="editor"></div>
          <div class="d-flex border rounded-1 list-group p-3 gap-3 flex-row mt-2">
            <div class="form-floating flex-grow-1">
              <input type="number" class="form-control" placeholder="모금 목표 금액(최대 1억)" name="goalamount" id="goalamount" value="${board.round.goalamount }" min="0" max="100000000" required>
              <label for="goalamount">모금 목표 금액(최대 1억)</label>
            </div>
            
            <div class="form-floating flex-grow-1">
              <input type="text" class="form-control" placeholder="마감일" name="voiddate" id="voiddate" value="${board.round.voiddate }" required>
              <label for="voiddate">마감일</label>
            </div>
          </div>
          
          <!-- 히든으로 값 전달 -->
          <input type="hidden" name="content" id="content">
          <input type="hidden" name="mno" value="${board.mno}">
          <input type="hidden" name="status" value="${board.status}">
          <input type="hidden" name="drno" value="${board.drno}">
          
          
          
          <div class="float-end mt-4">
            <a href="${cp }/board/list" class="btn btn-outline-primary me-2">취소</a>
            <button class="btn btn-primary me-2">수정하기</button>
          </div>
        	
        </form>
      </main>

    </div>

<%@ include file="../common/footer.jsp" %>
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
  <script>
    $(_ => {
	  const content = `${board.content}`;	
    
      const editor = new toastui.Editor({
        el: document.querySelector('#editor'), // 에디터를 표시할 요소
        height: '500px',
        initialEditType: 'markdown',  // 'wysiwyg' 또는 'markdown'
        previewStyle: 'vertical',     // 'vertical' 또는 'tab'
        initialValue: content,
      });

      flatpickr("#voiddate", {
        dateFormat: "Y-m-d",   // Y-m-d
        locale: "ko",          // 한국어
        minDate: new Date().fp_incr(15), // 오늘로부터 15일 이후 부터만 선택 가능
        maxDate: new Date().fp_incr(180), // 최대 6개월 뒤까지 선택 가능
        disableMobile: true, // 모바일 기본 날짜 선택기 비활성화
        animate: "true",
        allowInput: false
      });
	
      $('#writeForm').on('submit', function () {
    	  event.preventDefault();
    	  if($("#voiddate").val().trim() === ""){
    		  alert("마감일을 선택해주세요.");
    		  return;
    	  }
    	  
    	  
    	  const markdown = editor.getMarkdown(); // 또는 editor.getHTML();
    	  console.log(markdown);
    	  $('#content').val(markdown);
    	  
    	  
    	  this.submit();
    	});
      
      $("#goalamount").on("keyup", function(e){
    	  let num = e.target.value;
    	  console.log(num);
    	  if (/^0[0-9]+/.test(num)) {
    		  num = num.replace(/^0+/, '');
    		  }
          
          let value = parseInt(e.target.value, 10);
          // 1억보다 크면 1억으로 고정
          if (value > 100000000) {
            e.target.value = 100000000;
          }

          // 음수 방지
          if (value < 0) {
            e.target.value = 0;
          }
        })
      
    })
  </script>
</body>
</html>