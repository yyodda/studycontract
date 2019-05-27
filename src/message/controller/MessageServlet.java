package message.controller;

import member.model.vo.Member;
import message.model.service.MessageService;
import message.model.vo.Message;

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

@WebServlet(name = "Message", value = {"/message.do"})
public class MessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("member");
        String sendId = member.getMemberId();
        System.out.println(sendId);
        String receiveId = request.getParameter("receiveId");
        System.out.println(receiveId);
        ArrayList<Message> messageArrayList = null;
        try {
            messageArrayList = new MessageService().getMyMessages(sendId,receiveId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("receiveId",receiveId);
        request.setAttribute("messages",messageArrayList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/message.jsp");
        requestDispatcher.forward(request,response);
    }
}
