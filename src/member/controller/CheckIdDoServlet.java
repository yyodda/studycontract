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

@WebServlet(name = "CheckIdDo",value = {"/checkId.do"})
public class CheckIdDoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String memberId = request.getParameter("memberId");
        Member member = null;
        try {
            member = new MemberService().searchToId(memberId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.setContentType("application/json; charset=utf-8");
        boolean msg;
        if(member==null){
            msg=true;
        }else {
            msg=false;
        }
        new Gson().toJson(msg, response.getWriter());
    }
}
