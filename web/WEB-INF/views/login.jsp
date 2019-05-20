<%--
  Created by IntelliJ IDEA.
  User: 이윤석
  Date: 2019-05-17
  Time: 오전 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>study_contact</title>
</head>
<body>
<div class="container">
    <jsp:include page="/WEB-INF/common/header.jsp"/>
    <div class="content">
        <div class="loginBox">
            <div class="row">
                <input class="tbox" type="text" name="memberId" placeholder="아이디">
            </div>
            <div class="row">
                <input class="tbox" type="password" name="memberPw" placeholder="비밀번호">
            </div>
            <div class="row">
                <button class="btn" onclick="login()">로그인</button>
            </div>
            <div class="row">
                <a href="find_id.html" class="find_btn">아이디 찾기</a>
                <a href="find_pw.html" class="find_btn">비밀번호 찾기</a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/common/footer.jsp"/>
</body>
<script>
    function login() {
        console.log("inlogin");
        var memberId = $("input[name='memberId']").val();
        var memberPw = $("input[name='memberPw']").val();
        $.ajax({
            method:"POST",
            url:"login.do",
            data:{memberId:memberId,memberPw:memberPw},
            success:function (data) {
                console.log("로그인성공함수");
                console.log(data);
                if(data.trim()==="success"){
                    alert("로그인성공");
                    location.href="/myPage.do";
                }
                else {
                    alert("로그인실패");
                }
            },
            error:function () {
                alert("ERROR!");
            }
        });
    }
</script>
</html>
