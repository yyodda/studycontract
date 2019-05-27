package member.controller;

import com.google.gson.Gson;
import member.model.service.MemberService;
import member.model.vo.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "SearchFriend", value = {"/search.do"})
public class SearchFriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchKeyword = request.getParameter("searchKeyword");
        String searchType = request.getParameter("searchType");

        ArrayList<Member> friends = null;
        if(searchKeyword!=null&&searchType!=null) {
            try {
                friends = new MemberService().searchToType(searchType, searchKeyword);
                for(Member friend : friends){
                    System.out.println(friend);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            new Gson().toJson(friends,response.getWriter());
        }
        else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/search.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
