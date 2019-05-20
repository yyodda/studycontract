package member.controller;

import member.model.service.MemberService;
import member.model.vo.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "MyPageDo", value = {"/myPage.do"})
public class MyPageDoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session.getAttribute("member")!=null){
            System.out.println("MyPage : session is not null");
            Member member = (Member)session.getAttribute("member");

            ArrayList<Member> friends = null;
            try {
                friends = new MemberService().getFriends(member.getMemberId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("FRIENDS");
            for (Member friend : friends){
                System.out.println(friend);
            }
            request.setAttribute("friends", friends);
            String url = "/WEB-INF/views/myPage.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
            requestDispatcher.forward(request,response);
        }
    }
}
