package member.controller;

import member.model.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegisterFriend", value = {"/registerFriend.do"})
public class RegisterFriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String myId = request.getParameter("myId");
        String friendId = request.getParameter("friendId");
        System.out.println("myId : "+myId+", friendId : "+friendId);
        int result = 0;
        try {
            result = new MemberService().registerFriend(myId,friendId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.getWriter().print(result);
    }
}
