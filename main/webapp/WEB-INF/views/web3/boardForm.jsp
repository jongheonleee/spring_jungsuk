<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <title>Document</title>
</head>
<body>
<h2>게시물 읽기</h2>
<form action="" id="form">
    <input type="text" name="bno">
    <input type="text" name="title">
    <textarea name="content" id="" cols="30" rows="10"></textarea>
    <input type="text" name="writer">
    <button type="button" id="writeBtn" class="btn">등록</button>
    <button type="button" id="modifyBtn" class="btn">수정</button>
    <button type="button" id="removeBtn" class="btn">삭제</button>
    <button type="button" id="listBtn" class="btn">목록</button>
</form>
<script>
    $(document).ready(function () {
      $("#writeBtn").on('click', function () {
        let form = $("#form");
        form.attr("action", "<c:url value = '/board/write'/>");
        form.attr("method", "post");
        form.submit();
      })
    })
</script>
</body>
</html>

