<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>나의 게시판</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value='css/bootstrap.css'/>">
    <link rel="stylesheet" href="<c:url value='css/custom.css'/>">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
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
<div style="margin-bottom: 50px"></div>
<script>


</script>
</nav>
<div class="container">
    <h1>MyBoard<br></h1>
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
                        <td><a href="<c:url value='/board/read?page=${ph.getPage()}&pageSize=${ph.getPageSize()}&bno=${boardDTO.bno}'/>">${boardDTO.title}</a></td>
                        <td>${boardDTO.writer}</td>
                        <td>${boardDTO.reg_date}</td>
                        <td>${boardDTO.view_cnt}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <%--            <form name = "p_search">--%>
            <%--                <input type="button" value="검색" onclick="nwindow(<%=boardID%>)"/>--%>
            <%--            </form>--%>
            <a href="<c:url value='/board/write?page=${ph.getPage()}&pageSize=${ph.getPageSize()}'/>" class="btn-primary pull-right">글쓰기</a>
        </div>
    </div>


    <div class=container style="text-align:center">
        <c:if test="${ph.totalCnt==null || ph.totalCnt==0}">6
            <div> 게시물이 없습니다. </div>
        </c:if>
        <c:if test="${ph.totalCnt!=null && ph.totalCnt!=0}">
            <c:if test="${ph.prevPage}">
                <a href="<c:url value="/board/list?page=${ph.beginPage-1}&pageSize=${ph.pageSize}"/>">&lt;</a>
            </c:if>
            <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                <a class="page ${i==ph.page? "paging-active" : ""}" href="<c:url value="/board/list?page=${i}&pageSize=${ph.pageSize}"/>">${i}</a>
            </c:forEach>
            <c:if test="${ph.nextPage}">
                <a href="<c:url value="/board/list?page=${ph.endPage+1}&pageSize=${ph.pageSize}"/>">&gt;</a>
            </c:if>
        </c:if>
    </div>
</div>

<script>
</script>
</body>
</html>
