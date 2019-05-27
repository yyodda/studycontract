package message.controller;

import member.model.vo.Member;
import message.model.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "SendMessage", value={"/sendMessage.do"})
public class SendMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("member");
        String sendId = member.getMemberId();
        String receiveId = request.getParameter("receiveId");
        String messageContent = request.getParameter("messageContent");
        System.out.println("send to: " + receiveId);
        System.out.println("send content: " + messageContent);
        try {
            int result = new MessageService().insertMessage(sendId,receiveId,messageContent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
