package common.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Forward")
public class ForwardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String path = "/WEB-INF/views";
        String uri = request.getRequestURI();
        if(session!=null&&session.getAttribute("member")!=null){
            request.getRequestDispatcher("myPage.do").forward(request,response);
        }
        System.out.println("Forward to : "+uri+".jsp");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path+uri+".jsp");
        requestDispatcher.forward(request,response);
    }
}
