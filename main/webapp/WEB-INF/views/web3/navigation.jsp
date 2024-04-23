<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="navbar">
    <a href="<c:url value = '/'/>">홈</a>
    <a href="<c:url value ='/board/list'/>">글목록</a>
    <a href="<c:url value ='/board/write?toURL=${ param.toURL }'/>">글쓰기</a>
    <a href="<c:url value = '/login/login?toURL=${ param.toURL }'/>">로그인</a>
    <a href="<c:url value='/register/add'/>">회원가입</a>
    <a href="<c:url value = '/login/logout'/>" class="right">로그아웃</a>
</div>
