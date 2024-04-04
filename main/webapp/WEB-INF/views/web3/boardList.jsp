<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
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
      /* Column container */
      .row {
        display: -ms-flexbox; /* IE10 */
        display: flex;
        -ms-flex-wrap: wrap; /* IE10 */
        flex-wrap: wrap;
      }

      /* Create two unequal columns that sits next to each other */
      /* Sidebar/left column */
      .side {
        -ms-flex: 30%; /* IE10 */
        flex: 30%;
        background-color: #f1f1f1;
        padding: 20px;
      }

      /* Main column */
      .main {
        -ms-flex: 70%; /* IE10 */
        flex: 70%;
        background-color: white;
        padding: 20px;
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

      .wrap{
      }

      #userArea{
        text-align:center;
        color: #1abc9c;
      }



    </style>
</head>
<body>
<!-- 헤더 부분, 고정적인 부분 공통처리-->
<jsp:include page="header.jsp" flush="false"/>
<jsp:include page="navigation.jsp" flush="false"/>

<!-- 실제 내용, 달라지는 부분  -->
<div class="row">
    <div class="side">
        <div class="wrap">
            <h1 id="userArea">🙋🏻‍♂️ Hello "${pageContext.request.session.getAttribute('id')}"</h1>
        </div>
    </div>
</div>

<table border="1">
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>이름</th>
        <th>등록일</th>
        <th>조회수</th>
    </tr>
    <c:forEach var="boardDTO" items="${list}">
        <tr>
            <td>${boardDTO.bno}</td>
            <td>${boardDTO.title}</td>
            <td>${boardDTO.writer}</td>
            <td>${boardDTO.reg_date}</td>
            <td>${boardDTO.view_cnt}</td>
        </tr>
    </c:forEach>
</table>

<!-- 푸 부분, 고정적인 부분 공통처리-->
<jsp:include page="footer.jsp" flush="false"/>
</body>
</html>
