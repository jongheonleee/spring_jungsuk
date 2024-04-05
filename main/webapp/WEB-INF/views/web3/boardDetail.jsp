<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="true"%>
<html>
<head>
    <title>글내용</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <style>
      /* Style the body */
      body {
        font-family: Arial, Helvetica, sans-serif;
        margin: 0;
      }

      /* Header/logo Title */
      .header {
        padding: 80px;
        text-align: center;
        background: #1abc9c;
        color: white;
      }

      /* Increase the font size of the heading */
      .header h1 {
        font-size: 40px;
      }

      .footer {
        padding: 40px;
        text-align: center;
        background: #ddd;
      }
      /* Style the top navigation bar */
      .navbar {
        overflow: hidden;
        background-color: #333;
      }

      /* Style the navigation bar links */
      .navbar a {
        float: left;
        display: block;
        color: white;
        text-align: center;
        padding: 14px 20px;
        text-decoration: none;
      }

      /* Right-aligned link */
      .navbar a.right {
        float: right;
      }

      /* Change color on hover */
      .navbar a:hover {
        background-color: #ddd;
        color: black;
      }
    </style>
</head>
<body>
<jsp:include page="header.jsp" flush="false"/>
<jsp:include page="navigation.jsp" flush="false"/>
<form id="form">
    번호 : <input type="text" name="bno" value = ${boardDTO.bno}><br>
    제목 : <input type="text" name="title" value = ${boardDTO.title}><br>
    글쓴이 : <input type="text" name="writer" value= ${boardDTO.writer}><br>
    내용 : <textarea name="content" cols="30" rows="10" >
        ${boardDTO.content}
          </textarea><br>
</form>
<button id="modifyBtn" type="button">수정</button>
<button id="writeBtn" type="button">등록</button>
<button id="removeBtn" type="button">삭제</button>
<jsp:include page="footer.jsp" flush="false"/>
<script>
  $(document).ready(function () {
    $("#writeBtn").on('click', function() {
      const form = $("#form");
      form.attr("action", "<c:url value = '/board/write'/>");
      form.attr("method", "post");
      form.submit();
    })

    $("#modifyBtn").on('click', function() {
      const form = $("#form");
      form.attr("action", "<c:url value = '/board/modify'/>");
      form.attr("method", "post");
      form.submit();
    })

    $("#removeBtn").on('click', function() {
      const form = $("#form");
      form.attr("action", "<c:url value = '/board/remove'/>");
      form.attr("method", "post");
      form.submit();
    })
  })
</script>
</body>
</html>
