<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="modal fade" id="feedModal" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog modal-xl modal-dialog-centered">
    <div class="modal-content" style="border-radius: 10px; overflow: hidden;">

      <div class="row g-0">
        <!-- 왼쪽: 이미지 -->
        <div class="col-md-6 d-flex align-items-center justify-content-center bg-dark">
          <img src="https://placehold.co/600x600" class="img-fluid" id="feedImg" style="object-fit: cover; max-height: 600px; max-width: 600px; width: 100%; height: 100%;" alt="게시글 이미지">
        </div>

        <!-- 오른쪽: 게시글 정보 -->
        <div class="col-md-6 bg-white">
          <div class="p-4 d-flex flex-column" style="height: 100%; overflow-y: auto;">

            <!-- 작성자 -->
            <div class="d-flex align-items-center mb-4">
              <img src="https://placehold.co/40x40" id="profileImg" class="rounded-circle me-2" alt="프로필">
              <strong id="memberNickname">사용자닉네임</strong>
              <button type="button" class="btn-close ms-auto" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <!-- 게시글 내용 -->
            <div id="viewer" class="flex-grow-1 mb-3"></div>

            <!-- 댓글 -->
            <div class="mb-3">
              <ul class="d-flex flex-column gap-2 m-0 p-0" id="replys" style="max-height: 200px; overflow-y: auto;">
                  <li class="small m-0 d-flex justify-content-between reply" data-rno="\${r.rno}">
                    <div class="d-flex gap-2 content-wrap"><strong>\${r.nickname}</strong> <p class="m-0 reply-content">\${r.content}</p></div>
                    <div class="dropdown float-end">
                      <label class="d-block" data-bs-toggle="dropdown"><i class="fa-solid fa-ellipsis-vertical small text-muted" style="width: 20px; height: 20px;" ></i>
                      </label>
                      <ul class="dropdown-menu dropdown-menu-end">
                        <li><a class="dropdown-item btn-edit" href="#">수정</a></li>
                        <li><a class="dropdown-item btn-delete" href="#">삭제</a></li>
                      </ul>
                    </div>
                  </li>
              </ul>
            </div>

            <!-- 입력 -->
             <c:if test="${not empty member}">
            <form class="d-flex border-top pt-2">
              <input type="text" class="form-control me-2 border-0" id="content" placeholder="댓글 달기...">
              <button class="btn btn-primary btn-write-submit" style="width: 120px;">댓글 작성</button>
              <input type="hidden" id="writer" value="${member.mno}">
              <input type="hidden" id="bno" value="">
            </form>
              </c:if>
          </div>
        </div>
      </div>

    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script>
  $("#masonry-container").on("click", ".card", function(){
    event.preventDefault();
    const bno = $(this).data("bno");
    const cp = '${pageContext.request.contextPath}';
    $.ajax({
      url: cp + '/api/feed/view?bno=' + bno,
      method: "GET",
      dataType: "json",
      success: function(data) {
        $("#feedModal #feedImg").attr("src", data.board.thumbnail);
        $("#memberNickname").text(data.board.nickname);
        $("#viewer").text(data.board.content);
        $("#bno").val(data.board.bno);
        let replyStr = "";
        
        for(let r of data.replys){
          replyStr += `<li class="small m-0 d-flex justify-content-between reply" data-rno="\${r.rno}">
              <div class="d-flex gap-2 content-wrap"><strong>\${r.nickname}</strong> <p class="reply-content m-0">\${r.content}</p></div>
              <div class="dropdown float-end">
                <label class="d-block" data-bs-toggle="dropdown"><i class="fa-solid fa-ellipsis-vertical small text-muted" style="width: 20px; height: 20px;" ></i>
                </label>
                <ul class="dropdown-menu dropdown-menu-end">
                  <li><a class="dropdown-item btn-edit" href="#">수정</a></li>
                  <li><a class="dropdown-item btn-delete" href="#">삭제</a></li>
                </ul>
              </div>
            </li>`;
        }

        
        $("#replys").html(replyStr);
        $("#feedModal").modal("show");
      },
      error: function () {
      alert("피드 로딩 실패🥲");
      }
    });
  });

  const content = `${board.content}`.replaceAll("\\n", "\n").replaceAll("\\\"", "\"");
  const viewer = new toastui.Editor.factory({
    el: document.querySelector('#viewer'),
    viewer: true,
    initialValue: content
  });
</script>




<script>
    const url = "${cp}" + "/reply/";

    
    function makeReplyLi(r) {
      return `<li class="small m-0 d-flex justify-content-between reply" data-rno="\${r.rno}">
		      <div class="d-flex gap-2 content-wrap"><strong>\${r.nickname}</strong> <p class="reply-content m-0">\${r.content}</p></div>
		      <div class="dropdown float-end">
		        <label class="d-block" data-bs-toggle="dropdown"><i class="fa-solid fa-ellipsis-vertical small text-muted" style="width: 20px; height: 20px;" ></i>
		        </label>
		        <ul class="dropdown-menu dropdown-menu-end">
		          <li><a class="dropdown-item btn-edit" href="#">수정</a></li>
		          <li><a class="dropdown-item btn-delete" href="#">삭제</a></li>
		        </ul>
		      </div>
		    </li>`;
    }
    
    function list(bno, lastRno) {
      lastRno = lastRno ? ("/" + lastRno) : '';
      let reqUrl = url + 'list/' + bno  + lastRno;
      
        $.ajax({
              url : reqUrl,
            success : function(data){
                if(!data || data.length === 0) {
                  $(".btn-reply-more").prop("disabled", true).text("추가 댓글이 없습니다");
                  return;
                }
                $(".btn-reply-more").removeClass("d-none");
                let str = '';
                for(let r of data) {
                    str += makeReplyLi(r);  
                }
                $("#reviews").append(str); // 교체, 추가
            }
        })
    }
    // list(bno);
                    	
                    	
                    		



    // 글쓰기 버튼 이벤트 btn-write-submit
    $(".btn-write-submit").click(function() {
      event.preventDefault();
        const result = confirm("등록하시겠습니까?");
        if(!result) return;

        const content = $("#content").val().trim();
        const mno = $("#writer").val().trim();
        const bno = $("#bno").val().trim();
        

        const obj = {content, mno, bno};

        $.ajax({
            url,
            method : 'POST',
            data : JSON.stringify(obj),
            success : function(data) {
                if(data.result) { 
                    // 작성된 댓글
                    console.log(data);
                    
                    if(data.reply){ // not null, not undefined
                      const strLi = makeReplyLi(data.reply);
                      $("#replys").prepend(strLi);
                      $("#content").val("");
                    }
                }
            }
        })
    })
    
    // 글수정 폼 활성화 btn-modify-form
    $(".reviews").on("click",".btn-modify-form",function() {
        console.log("글수정 폼");
        const rno = $(this).closest("li").data("rno");
        $.getJSON(url + rno, function(data){
            $("#reviewModal .modal-footer button").show().eq(0).hide();
            $("#content").val(data.content);
            $("#writer").val(data.mno);
            $("#reviewModal").data("rno", rno);
            console.log(data);
            modal.show();
        })
    })

    // 글 수정 버튼 이벤트 btn-modify-submit
    $(".btn-modify-submit").click(function() {
        const result = confirm("수정하시겠습니까?");
        if(!result) return;
        
        const rno = $("#reviewModal").data("rno");
        console.log(rno);
        
        const content = $("#content").val().trim();
        const mno = $("#writer").val().trim();
    
        
        const obj = {content, mno, rno};

        $.ajax({
            url : url + rno,
            method : 'PUT',
            data : JSON.stringify(obj),
            success : function(data) {
                if(data.result){
                    modal.hide();
                    // 재호출 (get)
                    $.getJSON(url + rno, function(data){
                      console.log(data);
                      // 문자열 생성
                      const strLi = makeReplyLi(data);
                      // rno를 가지고 수정할 li를 탐색
                      const $li = $(`.reviews li[data-rno=\${rno}]`);
                      console.log($li.html());
                      // replaceWith 내용교체
                      $li.replaceWith(strLi);
                    })
                }
            }
        })

        console.log("글수정 전송");
    })

    // 글 삭제 버튼 이벤트 btn-delete
    $("#feedModal").on("click",".btn-delete",function() {
        const result = confirm("삭제하시겠습니까?");
        if(!result) return;
        
        const $li = $(this).closest(".reply");
        const rno = $li.data("rno");
        console.log("글 삭제 전송");
        $.ajax({
            url : url + rno,
            method : 'DELETE',
            success : function(data) {
                if(data.result){
                    $li.remove();
                }
            }
        })
    })
    
    // 댓글 더보기 버튼 이벤트
    $(".btn-reply-more").click(_ => {
      // 현재 댓글 목록 중 마지막 댓글 번호를 가져오기
      const lastRno = $("#reviews p:last").data("rno");
      console.log(lastRno);
      
      list(bno, lastRno);
    })
        



    $("#feedModal").on("hide.bs.modal", function () {
      document.activeElement.blur(); // 포커스 날려서 경고 제거
    });


    $("#feedModal").on('click', '.btn-edit', function(e) {
      e.preventDefault();
	
      const li = $(this).closest('.reply');
      const rno = li.data('rno');
      const content = li.find('.reply-content').text().trim();

      console.log(li);
      console.log(rno);
      console.log(content);
	
     
    });
</script>