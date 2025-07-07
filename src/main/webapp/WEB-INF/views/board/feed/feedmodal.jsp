<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="modal fade" id="postModal1" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog modal-xl modal-dialog-centered">
    <div class="modal-content" style="border-radius: 10px; overflow: hidden;">

      <div class="row g-0">
        <!-- 왼쪽: 이미지 -->
        <div class="col-md-6 d-flex align-items-center justify-content-center bg-dark">
          <img src="https://placehold.co/600x600" class="img-fluid" style="object-fit: cover; max-height: 100%;" alt="게시글 이미지">
        </div>

        <!-- 오른쪽: 게시글 정보 -->
        <div class="col-md-6 bg-white">
          <div class="p-4 d-flex flex-column" style="height: 100%; overflow-y: auto;">

            <!-- 작성자 -->
            <div class="d-flex align-items-center mb-4">
              <img src="https://placehold.co/40x40" class="rounded-circle me-2" alt="프로필">
              <strong>사용자닉네임</strong>
              <button type="button" class="btn-close ms-auto" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <!-- 게시글 내용 -->
            <div class="mb-3 flex-grow-1">
              <p>여기에 게시글 내용을 적을 수 있어요. 긴 텍스트도 스크롤 가능합니다.</p>
            </div>

            <!-- 댓글 -->
            <div class="mb-3">
              <div style="max-height: 200px; overflow-y: auto;">
                <p class="small"><strong class="me-2">user1</strong> 멋져요!</p>
                <p class="small"><strong class="me-2">user2</strong> 좋은 글입니다.</p>
                <!-- 필요하면 반복 -->
              </div>
            </div>

            <!-- 입력 -->
            <form class="d-flex border-top pt-2">
              <input type="text" class="form-control me-2 border-0" placeholder="댓글 달기...">
              <button class="btn btn-primary" type="submit" style="width: 120px;">댓글 작성</button>
            </form>

          </div>
        </div>
      </div>

    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>