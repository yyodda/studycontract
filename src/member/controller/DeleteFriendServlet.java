package member.controller;

import member.model.service.MemberService;
import member.model.vo.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteFriend", value = {"/deleteFriend.do"})
public class DeleteFriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String myId = ((Member)session.getAttribute("member")).getMemberId();
        String friendId = request.getParameter("friendId");
        int result = 0;
        try {
            result = new MemberService().deleteFriend(myId, friendId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.getWriter().print(result);
    }
}
