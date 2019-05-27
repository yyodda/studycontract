<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 이윤석
  Date: 2019-05-20
  Time: 오후 9:00
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

        <section class="searchBox">
            <div class="inputBox">
                <form id="searchFriend">
                    <div class="row">
                        <div class="labelBox">
                            <label>
                                <input type="radio" name="searchType" value="searchForId" checked>
                                <span>아이디</span>
                            </label>
                            <label>
                                <input type="radio" name="searchType" value="searchForName">
                                <span>이름</span>
                            </label>
                            <label>
                                <input type="radio" name="searchType" value="searchForPhone">
                                <span>핸드폰번호</span>
                            </label>
                        </div>
                    </div>
                    <div class="row">
                        <input type="text" class="tbox full" name="searchKeyword">
                        <input type="text" style="display: none;">
                    </div>
                    <div class="row">
                        <button type="button" class="btn full" onclick="searchFriend();">친구 찾기</button>
                    </div>
                </form>
                <div class="row">
                    <!-- 버튼 클륵 시 조회결과 표시 -->
                    <div class="findIdResult" id="friendResult">
                    </div>
                </div>
            </div>
        </section>
    </div>
    <script>
        function searchFriend() {
            var formData = $("#searchFriend").serialize();
            console.log(formData);
            $.ajax({
                url:"search.do",
                method:"POST",
                data:formData,
                dataType:"json",
                success:function(friends){
                    var str = "";
                    for(var i = 0;i<friends.length;i++){
                        str += "<div class='row'>";
                        str += "<div class='subject'>아이디</div>";
                        str += "<div class='searchResultText'>"+friends[i].memberId+"</div>";
                        str += "</div>";

                        str += "<div class='row'>";
                        str += "<div class='subject'>이름</div>";
                        str += "<div class='searchResultText'>"+friends[i].memberName+"</div>";
                        str += "</div>";

                        str += "<div class='row'>";
                        str += "<div class='subject'>핸드폰번호</div>";
                        str += "<div class='searchResultText'>"+friends[i].phone+"</div>";
                        str += "</div>";

                        str += "<div class='row'>";
                        str += "<div class='subject'>성별</div>";
                        str += "<div class='searchResultText'>"+friends[i].gender+"</div>";
                        str += "</div>";

                        str += "<div class='row'>";
                        str += "<button type='button' class='btn full' onclick='registerFriend(\"${sessionScope.member.memberId}\",\""+friends[i].memberId+"\")'>친구 등록</button>";
                        str += "</div>";
                    }
                    $("#friendResult").html(str);
                },
                error:function () {
                    alert("친구찾기폼 전송 오류");
                }
            });
        }
        function registerFriend(myId,friendId) {
            if(myId === friendId){
                alert("나 자신은 인생의 영원한 친구입니다.");
            }
            else{
                $.ajax({
                    url:"registerFriend.do",
                    method:"post",
                    data:{myId:myId,friendId:friendId},
                    success:function(result){
                        console.log(result);
                        if(result==="1") {
                            alert("친구 추가 완료");
                        }
                        else if(result==="0"){
                            alert("이미 친구 입니다.");
                        }
                        else{
                            alert("오류가 발생했습니다.");
                        }
                    },
                    error:function () {
                        alert("친구 추가 오류");
                    }
                });
            }
        }
    </script>
    <jsp:include page="/WEB-INF/common/footer.jsp"/>
</div>
</body>
</html>