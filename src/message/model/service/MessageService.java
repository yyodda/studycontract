package message.model.service;

import common.singleton.JDBCTemplate;
import jdk.nashorn.internal.scripts.JD;
import member.model.dao.MemberDao;
import member.model.vo.Member;
import message.model.dao.MessageDao;
import message.model.vo.Message;
import message.model.vo.MessageInfo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class MessageService {

    public ArrayList<Message> getMyMessages(String sendId, String receiveId) throws SQLException {
        Connection connection = JDBCTemplate.getConnection();
        int result = new MessageDao().updateReadStatus(connection,sendId,receiveId);
        ArrayList<Message> messageArrayList = new MessageDao().getMyMessages(connection,sendId,receiveId);
        if(result > 0){
            JDBCTemplate.commit(connection);
        }
        else {
            JDBCTemplate.rollback(connection);
        }
        result = new MessageDao().updateMessageInfo(connection,sendId,receiveId);
        if(result>0){
            JDBCTemplate.commit(connection);
        }
        else {
            JDBCTemplate.rollback(connection);
        }
        JDBCTemplate.close(connection);
        return messageArrayList;
    }

    public int insertMessage(String sendId, String receiveId, String messageContent) throws SQLException {
        Connection connection = JDBCTemplate.getConnection();
        int result = new MessageDao().insertMessage(connection,sendId,receiveId,messageContent);
        if(result>0){
            JDBCTemplate.commit(connection);
        }else {
            JDBCTemplate.rollback(connection);
        }
        int infoResult = new MessageDao().updateMessageInfo(connection, sendId, receiveId);
        if(infoResult>0){
            JDBCTemplate.commit(connection);
        }
        else {
            JDBCTemplate.rollback(connection);
        }
        JDBCTemplate.close(connection);
        return result;
    }

    public ArrayList<MessageInfo> getMyMessageInfo(String memberId) throws SQLException {
        Connection connection = JDBCTemplate.getConnection();
        ArrayList<MessageInfo> messageInfoArrayList = new MessageDao().getMessageInfo(connection,memberId);
        JDBCTemplate.close(connection);
        return messageInfoArrayList;
    }
}
