<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
<script src="https://cdn.quilljs.com/1.3.6/quill.min.js"></script>
<style>
	.form--control {
		width: 1110px;
	}

	.board--write {
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.container {
		margin-bottom: 30px;
	}

	.table td {
		border-top: none;
	}

	#editor-container {
		height: 500px;
	}
</style>
<main>
	<h2 class="page--title">여행일지</h2>
	<hr>
	<br>

	<form action="/board/update/${id}" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${id}">
		<table class="table">
			<tr>
				<td><input class="form-control" id="title" type="text" name="title" value="${boardDto.title}"></td>
			</tr>
		</table>
		<div class="container">
			<!-- Quill 에디터가 들어갈 요소 -->
			<div id="editor-container">${boardDto.content}</div>
			<input type="hidden" name="content" id="quillContent">
		</div>
		<div class="custom-file">
			<input type="file" class="custom-file-input" id="customFile" accept=".jpg, .jpeg, .png" name="file">
			<label class="custom-file-label" for="customFile">${boardDto.fileName}</label>
		</div>
		<div style="margin-top: 40px;" class="d-flex justify-content-center">
			<button type="button" id="backPage" class="blue--btn--small" style="margin-right: 60px; padding-left: 9px; background-color: gray" onclick="location.href='/board/list/1'">
				<ul class="d-flex justify-content-center" style="margin: 0;">
					<li><span class="material-symbols-outlined material-symbols-outlined-white" style="font-size: 22px; margin-top: 3px; margin-right: 5px;">keyboard_backspace</span>
					<li>취소
				</ul>
			</button>
			<button type="submit" class="blue--btn--small" id="writeBtn">
				<ul class="d-flex justify-content-center" style="margin: 0;">
					<li style="margin-right: 4px;">입력 완료
					<li><span class="material-symbols-outlined material-symbols-outlined-white" style="font-size: 22px; margin-top: 3px;">done</span>
				</ul>
			</button>
		</div>
	</form>
</main>
<script>
	$(document).ready(function() {
		// Quill 에디터 초기화
		var quill = new Quill('#editor-container', {
			theme: 'snow',
			placeholder: '여행일지 내용을 입력하세요...',
			modules: {
				toolbar: [
					[{ header: [1, 2, false] }],
					['bold', 'italic', 'underline'],
					[{ 'list': 'ordered' }, { 'list': 'bullet' }],
					['link', 'image'],
					[{ 'align': [] }],
					['clean'] // 서식 제거
				]
			}
		});

		// 제출할 때 Quill 데이터를 form의 hidden input에 복사
		$('form').on('submit', function() {
			$('#quillContent').val(quill.root.innerHTML);
		});
	});

	$(".custom-file-input").on("change", function() {
		var fileName = $(this).val().split("\\").pop();
		$(this).siblings(".custom-file-label").addClass("selected").html(fileName);
	});

	// 뒤로가기 버튼
	$(document).ready(function() {
		$("#backPage").on("click", function() {
			event.preventDefault();

			var boardId = $("input[name='boardId']").val();
			var backConfirm = confirm("작성중인 게시글이 저장되지 않습니다.\n정말 나가시겠습니까?");
			if (backConfirm) {
				window.history.back();
			} else {
				return false;
			}
		});
	});
</script>

<input type="hidden" name="menuName" id="menuName" value="여행일지">

<%@ include file="../layout/footer.jsp"%>
