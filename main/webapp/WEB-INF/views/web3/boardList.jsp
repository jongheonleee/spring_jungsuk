<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>나의 게시판</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value='resources/css/bootstrap.css'/>">
    <link rel="stylesheet" href="<c:url value='resources/css/custom.css'/>">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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


      .paging {
        color: black;
        width: 100%;
        align-items: center;
      }

      .page {
        color: black;
        padding: 6px;
        margin-right: 10px;
      }
      .paging-active {
        background-color: rgb(216, 216, 216);
        border-radius: 5px;
        color: rgb(24, 24, 24);
      }

      .paging-container {
        width:100%;
        height: 70px;
        display: flex;
        margin-top: 50px;
        margin : auto;
      }

      body,h1,h2,h3,h4,h5,h6 {font-family: "Lato", sans-serif}
      .w3-bar,h1,button {font-family: "Montserrat", sans-serif}
      .fa-anchor,.fa-coffee {font-size:200px}

      .paging-active {
        background-color: rgb(216, 216, 216);
        border-radius: 5px;
        color: rgb(24, 24, 24);
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

<div class="container">
    <div class="row">
        <table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
            <thead>
            <tr>
                <th style="background-color: #eeeee; text-align: center;">번호</th>
                <th style="background-color: #eeeee; text-align: center;">제목</th>
                <th style="background-color: #eeeee; text-align: center;">작성자</th>
                <th style="background-color: #eeeee; text-align: center;">작성일</th>
                <th style="background-color: #eeeee; text-align: center;">조회수</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="boardDTO" items="${list}">
                <tr>
                    <td>${boardDTO.bno}</td>
                    <td><a href="<c:url value='/board/read?currPage=${ph.currPage}&pageSize=${ph.PAGE_SIZE}&bno=${boardDTO.bno}'/>">${boardDTO.title}</a></td>
                    <td>${boardDTO.writer}</td>
                    <td>${boardDTO.reg_date}</td>
                    <td>${boardDTO.view_cnt}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- 페이징 처리 -->
<br>
<div class="paging-container">
    <div class="paging" style="text-align: center">
        <c:if test="${ph.totalCnt == 0 || ph.totalCnt == 0}">
            <div>게시물 없음</div>
        </c:if>

        <c:if test="${ph.totalCnt != null && ph.totalCnt != 0}">
            <c:if test="${ph.showPrev}">
                <a class="page" href="<c:url value="/board/list?currPage=${ph.currPage-1}&pageSize=${ph.PAGE_SIZE}"/>">&lt;</a>
            </c:if>
            <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                <a class="page" href="<c:url value="/board/list?currPage=${i}&pageSize=${ph.PAGE_SIZE}"/>"> ${i} </a>
            </c:forEach>
            <c:if test="${ph.showNext}">
                <a class="page" href="<c:url value="/board/list?currPage=${ph.currPage+1}&pageSize=${ph.PAGE_SIZE}"/>">&gt;</a>
            </c:if>
        </c:if>
    </div>
</div>

<!-- 푸 부분, 고정적인 부분 공통처리-->
<jsp:include page="footer.jsp" flush="false"/>
</body>
</html>
