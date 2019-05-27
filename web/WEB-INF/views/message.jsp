<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 이윤석
  Date: 2019-05-22
  Time: 오전 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">	<title>study_contact</title>
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

        <section class="noteViewBox">

            <div class="noteGroup">
                <c:forEach var="message" items="${requestScope.messages}" varStatus="index">
                    <c:if test="${message.sendId eq sessionScope.member.memberId}">
                        <div class="noteBox">
                            <div class="myMsg">${message.messageStatus}${message.messageContent}(${message.messageDate})</div>
                        </div>
                    </c:if>
                    <c:if test="${message.sendId ne sessionScope.member.memberId}">
                        <div class="noteBox">
                            <div class="youMsg">${message.messageContent}(${message.messageDate})${message.messageStatus}</div>
                        </div>
                    </c:if>
                </c:forEach>
                <div class="noteMsgSendBox">
                    <textarea class="tbox full" id="messageContent"></textarea>
                    <button type="button" class="btn full" onclick="sendMessage();">전송</button>
                </div>
            </div>
        </section>
    </div>
    <jsp:include page="/WEB-INF/common/footer.jsp"/>
</div>
<script>
function sendMessage() {
    var receiveId = '${receiveId}';
    var messageContent = $("#messageContent").val();
    console.log(messageContent);
    $.ajax({
        url:"sendMessage.do",
        method:"post",
        data:{receiveId:receiveId,messageContent:messageContent},
        complete:function () {
            location.reload();
        },
        error:function () {
            alert("메세지 전송 오류!")
        }
    });
}
</script>
</body>
</html>