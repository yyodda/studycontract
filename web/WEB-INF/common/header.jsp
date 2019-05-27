<%--
  Created by IntelliJ IDEA.
  User: 이윤석
  Date: 2019-05-17
  Time: 오전 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="../../css/reset.css">
<link rel="stylesheet" type="text/css" href="../../css/style.css">

<script src="../../js/jquery-3.1.1.min.js"></script>

<c:set var="path" value="/WEB-INF/views/"/>
<c:set var="uri" value="${pageContext.request.requestURI}"/>

<c:set var="index" value="${path}${'index.jsp'}"/>
<c:set var="login" value="${path}${'login.jsp'}"/>
<c:set var="join" value="${path}${'join.jsp'}"/>
<c:set var="myPage" value="${path}${'myPage.jsp'}"/>
<c:set var="search" value="${path}${'search.jsp'}"/>
<c:set var="message" value="${path}${'message.jsp'}"/>
<c:set var="messageInfo" value="${path}${'messageInfo.jsp'}"/>




<c:choose>
    <c:when test="${uri eq index||uri eq login||uri eq join}">
        <header>
            <a href="/" class="logo">STUDY-CONTACT</a>
            <div class="menu">
                <c:choose>
                    <c:when test="${sessionScope.member eq null}">
                        <a href="login">로그인</a>
                        <a href="join">회원가입</a>
                    </c:when>
                    <c:when test="${sessionScope.member ne null}">
                        <a href="logout.do">로그아웃</a>
                        <a href="myPage.do">마이페이지</a>
                    </c:when>
                </c:choose>
            </div>
        </header>
    </c:when>
    <c:when test="${uri eq myPage||uri eq search||uri eq message||uri eq messageInfo}">
        <header>
            <a href="index" class="logo">STUDY-CONTACT</a>
            <div class="menu">
                <a href="myPage.do">주소록</a>
                <a href="boardList.html">게시판</a>
                <a href="logout.do">로그아웃</a>
            </div>
        </header>
    </c:when>
</c:choose>