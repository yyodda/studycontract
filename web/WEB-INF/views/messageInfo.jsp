<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 이윤석
  Date: 2019-05-21
  Time: 오후 8:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>study_contact</title>
</head>
<body>
<div class="container">
    <jsp:include page="/WEB-INF/common/header.jsp"/>
    <div class="content">
        <div class="subMenu">
            <a href="myPage.do">메인</a>
            <a href="search.do">친구 찾기</a>
            <a href="messageInfo">쪽지함</a>
            <a href="myInfoChk.html">내 정보수정</a>
            <a href="leave.html">회원 탈퇴</a>
        </div>

        <section class="noteListBox">
            <table>
                <thead>
                <tr>
                    <th>아이디</th>
                    <th>이름</th>
                    <th>마지막 메세지 내용</th>
                    <th>마지막 메세지 시간</th>
                    <th>쪽지내역</th>
                    <th>삭제</th>
                </tr>
                </thead>
                <tbody>
                <%--messageList--%>
                <c:forEach var="messageInfo" items="${messageInformation}" varStatus="index">
                    <tr>
                        <td>${messageInfo.receiveId}</td>
                        <td>${messageInfo.receiveName}</td>
                        <td>${messageInfo.recentMessageContent}${messageInfo.recentMessageCount}</td>
                        <td>${messageInfo.recentMessageDate}</td>
                        <td><a href="/message.do?receiveId=${messageInfo.receiveId}" class="btn">보기</a></td>
                        <td><a href="#none" class="btn remove">삭제</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="paging">
                <a href="#none">이전</a>
                <a href="#none" class="on">1</a>
                <a href="#none">2</a>
                <a href="#none">3</a>
                <a href="#none">다음</a>
            </div>

        </section>

    </div>
    <jsp:include page="/WEB-INF/common/footer.jsp"/>
</div>
</body>
</html>
