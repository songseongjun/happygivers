<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/head.jsp" %>
<style type="text/css">
.placeholder{background-color: transparent; font-size: 16px}
</style>
</head>
<body>
<%@ include file="../../common/header.jsp" %>
	<div class="container">
    	<main>
        	<h2 class="text-center my-5">피드 글 작성</h2>
	        <form class="my-4" id="writeForm" method="POST">
	          <div id="editor"></div>
			  	  <input type="hidden" name="content" id="content">
		          <input type="hidden" name="cno" value="10">
		          <input type="hidden" name="status" value="ACTIVE">
		          <input type="hidden" name="ctype" value="FEED">
		          <input type="hidden" name="mno" value="${member.mno }">
	          <div class="float-end mt-4">
	            <a href="${cp }/feed/list" class="btn btn-outline-primary me-2">취소</a>
	            <button class="btn btn-primary me-2">글쓰기</button>
	          </div>
	        </form>
      	</main>

    </div>

<%@ include file="../../common/footer.jsp" %>
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<script>
$(_ => {
    const editor = new toastui.Editor({
      el: document.querySelector('#editor'), // 에디터를 표시할 요소
      height: '500px',
      initialEditType: 'markdown',  // 'wysiwyg' 또는 'markdown'
      previewStyle: 'vertical',     // 'vertical' 또는 'tab'
      placeholder: '업로드한 이미지 중 첫 번째 이미지는 썸네일로 사용됩니다.',
      initialValue: ''
    });

	
    $('#writeForm').on('submit', function () {
  	  event.preventDefault();
  	  const markdown = editor.getMarkdown(); // 또는 editor.getHTML();

  	  $('#content').val(markdown);
  	  
  	  this.submit();
  	});
})
</script>

</body>
</html>