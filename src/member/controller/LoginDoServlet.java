package member.controller;

import com.google.gson.Gson;
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

@WebServlet(name = "LoginDo",value = {"/login.do"})
public class LoginDoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        System.out.println("dfdf");
//        System.out.println(((Member)(session.getAttribute("member"))).getMemberId());
        if(session!=null&&session.getAttribute("member")!=null){
            System.out.println("Error : already login");
            response.sendRedirect("/");
            return;
        }
        else{
            String memberId = request.getParameter("memberId");
            String memberPw = request.getParameter("memberPw");
            System.out.println("Try to login ID : "+memberId+", PW : "+memberPw);
            Member member = null;
            try {
                member = new MemberService().loginMember(memberId, memberPw);
                System.out.println(member);
                String msg ="";
                if(member !=null){
                    msg="success";
                }
                else{
                    msg="fail";
                }
                response.setContentType("application/json; charset=utf-8");
                new Gson().toJson(msg, response.getWriter());
                session.setAttribute("member",member);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
