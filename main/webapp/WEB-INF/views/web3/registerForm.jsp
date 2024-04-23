<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입 폼</title>
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
<!-- 헤더 부분, 고정적인 부분 공통처리-->
<jsp:include page="header.jsp" flush="false"/>
<%--<form action="<c:url value="/register/save"/>" method="post">--%>

<%--</form>--%>
<form action="<c:url value="/register/save"/>" method="post">
    <div class="title">Register</div>
    <div id="msg" class="msg"><form:errors path="id"/></div>
    <label for="">아이디</label>
    <input class="input-field" type="text" name="id" placeholder="8~12자리의 영대소문자와 숫자 조합">
    <label for="">비밀번호</label>
    <input class="input-field" type="text" name="pwd" placeholder="8~12자리의 영대소문자와 숫자 조합">
    <label for="">이름</label>
    <input class="input-field" type="text" name="name" placeholder="홍길동">
    <label for="">이메일</label>
    <input class="input-field" type="text" name="email" placeholder="example@fastcampus.co.kr">
    <label for="">생일</label>
    <input class="input-field" type="text" name="birth" placeholder="2020-12-31">
    <div class="sns-chk">
        <label><input type="checkbox" name="sns" value="facebook"/>페이스북</label>
        <label><input type="checkbox" name="sns" value="kakaotalk"/>카카오톡</label>
        <label><input type="checkbox" name="sns" value="instagram"/>인스타그램</label>
    </div>
    <button>회원 가입</button>
</form>
<!-- 푸터 부분, 고정적인 부분 공통처리 -->
<jsp:include page="footer.jsp" flush="false"/>
<script>
  function formCheck(frm) {
  }

  function setMessage(msg, element){
  }
</script>
</body>
</html>
