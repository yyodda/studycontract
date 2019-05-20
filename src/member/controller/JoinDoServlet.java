package member.controller;

import com.google.gson.Gson;
import member.model.service.MemberService;
import member.model.vo.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "JoinDo", value = "/join.do")
public class JoinDoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member member = new Member();

        member.setMemberId(request.getParameter("memberId"));
        member.setMemberPw(request.getParameter("memberPw"));
        member.setMemberName(request.getParameter("memberName"));
        member.setBirthDate(request.getParameter("birthDate"));
        member.setGender(request.getParameter("gender").toUpperCase());
        member.setPhone(request.getParameter("phone"));
        member.setEmail(request.getParameter("email"));
        member.setAddress(request.getParameter("address"));

        int result = 0;
        try {
            result = new MemberService().insertMember(member);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.setContentType("application/json; charset=utf-8");
        new Gson().toJson(result, response.getWriter());
    }
}
