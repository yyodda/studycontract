<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 이윤석
  Date: 2019-05-17
  Time: 오후 7:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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

        <section class="contactListBox">

            <div class="filterMenu">
                <a href="#none" class="on">이름순</a>
                <a href="#none">나이순</a>
                <a href="#none">최근접속일순</a>
            </div>
            <table>
                <thead>
                <tr>
                    <th>No</th>
                    <th>이름</th>
                    <th>나이</th>
                    <th>성별</th>
                    <th>핸드폰번호</th>
                    <th>이메일주소</th>
                    <th>최근로그인</th>
                    <th>쪽지</th>
                    <th>삭제</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="friend" items="${requestScope.friends}" varStatus="status">
                    <tr id='tr${status.count}'>
                        <td>${status.count}</td>
                        <td>${friend.memberName}</td>
                        <td>${friend.age}</td>
                        <td>${friend.gender}</td>
                        <td>${friend.phone}</td>
                        <td>${friend.email}</td>
                        <td>${friend.recentLogin}</td>
                        <td><a href="javascript:void(0);" class="btn" onclick="location.href='/message.do?receiveId=${friend.memberId}';">보내기</a></td>
                        <td><a href="javascript:void(0);" class="btn remove" onclick="deleteFriend($('#tr${status.count}'),'${friend.memberId}', this);">삭제</a></td>
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
    <script>
        function deleteFriend(tr,friendId, e) {
            if(confirm("친구 삭제?")){
                $.ajax({
                    url:"deleteFriend.do",
                    method:"post",
                    data:{friendId:friendId},
                    success:function(result){
                        if(result>0){
                            alert("지워졌습니다.");
                            // tr.remove();
                            $(e).parents('tr').remove();
                        }
                        else{
                            alert("삭제 실패");
                        }
                    },
                    error:function () {
                        alert("삭제 에러발생!")
                    }
                });
            }
        }
    </script>
    <jsp:include page="/WEB-INF/common/footer.jsp"/>
</div>
</body>
</html>
