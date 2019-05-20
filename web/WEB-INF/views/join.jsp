<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 이윤석
  Date: 2019-05-17
  Time: 오전 11:11
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
            <form id="joinForm">
                <div class="inputBox">
                    <div class="row">
                        <div class="subject">아이디 (6자~12자)
                            <a href="javascript:void(0)" onclick="checkId()" class="idChkBtn">중복확인</a>
                        </div>
                        <input class="tbox full" name="memberId" id="memberId" placeholder="">
                        <div class="infoMsg" id="checkIdMsg"></div>
                    </div>
                    <div class="row">
                        <div class="subject">비밀번호 (특수문자를 제외한 4~8자)</div>
                        <input class="tbox full" type="password" name="memberPw" id="memberPw" placeholder="">
                        <div class="infoMsg" id="checkPwMsg"></div>
                    </div>
                    <div class="row">
                        <div class="subject">비밀번호 재입력</div>
                        <input class="tbox full" type="password" placeholder="">
                        <div class="infoMsg">비밀번호가 일치하지 않습니다.</div>
                    </div>
                    <div class="row">
                        <div class="subject">이름</div>
                        <input class="tbox full" name="memberName" id="memberName" placeholder="">
                    </div>
                    <div class="row">
                        <div class="subject">생년월일 (ex 901015)</div>
                        <input class="tbox full" name="birthDate" id="birthDate"  placeholder="">
                    </div>
                    <div class="row">
                        <div class="subject">성별</div>
                        <div class="labelBox">
                            <label>
                                <input type="radio" name="gender" value="M"><span>남</span>
                            </label>
                            <label>
                                <input type="radio" name="gender" value="F"><span>여</span>
                            </label>
                        </div>

                    </div>
                    <div class="row">
                        <div class="subject">핸드폰번호(숫자만 입력)</div>
                        <input class="tbox full" name="phone" id="phone" placeholder="">
                    </div>
                    <div class="row">
                        <div class="subject">이메일</div>
                        <input class="tbox full" name="email" id="email" placeholder="">
                    </div>
                    <div class="row">
                        <div class="subject">주소</div>
                        <input class="tbox full" id="address" name="address" placeholder="">
                    </div>
                    <div class="row">
                        <button type="button" class="btn full" onclick="submitForm()">회원가입</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/common/footer.jsp"/>
<script>

    var regchk;
    function checkId() {
        if(regchk){
            var memberId = $("input[name='memberId']").val();
            $.ajax({
                method:"get",
                url:"/checkId.do",
                data:{memberId:memberId},
                success:function (msg) {
                    if(msg){
                        console.log(msg);
                        <c:set var="checkId" scope="request" value='true'/>
                        alert("사용 가능한 아이디 입니다.");
                    }
                    else{
                        console.log(msg);
                        <c:set var="checkId" scope="request" value='false'/>
                        alert("중복 된 아이디 입니다.");
                    }
                }
            });
        }
    };
    $("#memberId").keyup(function () {

        $("#checkIdMsg").html("");
        var memberId = $("#memberId").val();
        var regex = /^[a-zA-Z][a-zA-Z0-9]{5,11}$/;
        if(regex.test(memberId)){
            $("#checkIdMsg").html("사용 가능한 아이디 입니다.");
            $("#checkIdMsg").css("color","#01AEF0");
            regchk=true;
        }
        else {
            $("#checkIdMsg").html("6~12 글자를 입력하세요");
            $("#checkIdMsg").css("color","red");
            regchk=false;
        }
    });
    
    function submitForm() {
        var formData = $("#joinForm").serialize();
        console.log(formData);
        $.ajax({
            url:"join.do",
            method:"post",
            data:formData,
            success:function (result) {
                alert(result);
                location.href="index";
            },
            error:function () {
                alert('Error');
            }
        });
    }
</script>
</body>
</html>
