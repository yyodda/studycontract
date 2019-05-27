package message.controller;

import member.model.vo.Member;
import message.model.service.MessageService;
import message.model.vo.MessageInfo;

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

@WebServlet(name = "MessageInfo", value = {"/messageInfo"})
public class MessageInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("member");
        ArrayList<MessageInfo> messageInfoArrayList = null;
        try {
            messageInfoArrayList = new MessageService().getMyMessageInfo(member.getMemberId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("messageInformation",messageInfoArrayList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/messageInfo.jsp");
        requestDispatcher.forward(request,response);

    }
}
